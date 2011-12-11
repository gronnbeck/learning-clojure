; 	Just realized something with my previous approach.
; I can do several of optimizations, they're:
;	* Remember which primes we've generated
;	* To binary search instead of linear search
;	* Another logic a cannot name, but will explain below
; It's hard to explain but I think we can do some major optimizations
; by changing the logic a little bit.
;	The previous version was calculation the factors of a triangle number
; from the base (recalculating possible primes), even though it was just
; a product of an previously calculated number. By knowing this we can
; easily compute factors for this number.
;	The math behind my optimization (not proved in anyway – it's a long time since I used induction to prove anything):
; Let n be a triangle number, and let S be the set of it's factors. 
; If m is cn where c is a known constant, and D is the set of m's factors
; then m is the same as S union cD. 
;	Let's test if it works! 
; 	This also means that the number of divisors in D is the size of the set
; after S union cD. Is there a a way to compute this by just knowing the size
; of S instead of the acutal set S?
;	At the top of my head. I really don't know. So I'm going to program 
; it in a different way. However, if such calculation exists it'll probably
; speed up the algorithm a lot.

(ns p12
  (:require [clojure.set]))


; We can use factors-of? too calculate our constant c
(defn factors-of? [number in]
	(if (= 1 number)
		1
		(int (Math/floor (/ in number)))))
		
(defn fast-factors [n S m]
	"assumes that m is a result of c*n. 
	S is the set of factors of n, and returns the set
	of factors of m"
	(let [c (factors-of? n m)]
		(clojure.set/union S (set (map #(* c %) S))))) 
		; if anyone know a better way to do operation on all items on a set
		; that returns a set.. please tell me
		
(defn can-fast-factor? [n m]
	(= 0 (rem n m)))

; Let us see if fast factors actually work
; 	(fast-factors 8 #{1 2 4} 16) ; -> [1 2 4 8]
; Looks like it works.. Now to implement the rest

(defn triangle-number [number]
	"Calculates the number'th in the sequence of
	triangle numbers from 1"
	(loop [i 1 sum 0]
		(if (> i number)
		sum
		(recur (inc i) (+ sum i)))))

(declare prime?)
(declare memo-prime?)

(defn primes [from upto calculated]
	(loop [n (inc from)
		  primes calculated]
		(if (> n upto)
			primes
			(if (memo-prime? n)
				(recur (inc n) (conj primes n))
				(recur (inc n) primes)))))


(defn generate-primes [upto & [calculated]]
	"Instead of generating all the primes on the fly everytime,
	 we should be able to append the already saved primes. So we
	 can save resources in terms of performance"
	(if (nil? calculated)
		(primes 2 upto '(2 1))
		(primes (first calculated) upto calculated)))

(defn prime? [n]
	(if (some 
		#(and (= (rem n %) 0) (not (= n %)) (> % 1)) 
		(generate-primes (int (Math/ceil (Math/sqrt n)))))
	false
	true))
(def memo-prime? (memoize prime?))

; quick code to check if the opimzations has worked
; (time (println (generate-primes 28)))
; (time (println (generate-primes 28 '(11 7 5 3 2 1))))

(defn possible-divs [prime number]
	(loop [n 1 div #{}]
	(if (= n (factors-of? prime number))
		(if (= 0 (rem number (* prime n)))
			(conj div (* prime n))
			div)
		(if (= 0 (rem number (* prime n)))
			(recur (inc n) (conj div (* prime n)))
			(recur (inc n) div)))))

(defn the-divisor-loop [number known-primes]
	(loop [primes known-primes divisors #{1}]
		(if (= 0 (count primes))
			(conj divisors number)
			(if (= 0 (rem number (first primes)))
				(recur (next primes) 
					(clojure.set/union (possible-divs (first primes) number) divisors))
				(recur (next primes) divisors)))))
	
	
(defn the-divisors [number & [pre-generated-primes]]
	(if (nil? pre-generated-primes)
	(the-divisor-loop number (generate-primes number))
	(the-divisor-loop number pre-generated-primes)))

; Let's test this one..
; (time (println "Loading"))
; (println "Before prime? has gotten \"warm\"")
; (time (println (the-divisors 28)))
; (time (println (the-divisors 28)))
; (time (println (the-divisors 28 (generate-primes 28))))
; works quite well

(defn calculate-based-on-history [n history]
	"Not implemented.. Oh yeas it is"
	(loop [h history divs #{}]
		(if (nil? (first h))
			(conj divs n)
			(if (= 0 (rem n (first (first h))))
				(recur (next h) (clojure.set/union 
						divs
					   (fast-factors n
									 (last (first h))
									 (first (first h)))))
				(recur (next h) divs)))))

(defn calculate-divisors [n primes history]
	(if (some #(can-fast-factor? n %) history)
		(calculate-based-on-history n history)
		(the-divisors n primes)))
	
	
(defn problem12 [number-of-divisors]
	(loop [n 1 primes nil divisors #{}]
		(let [gen-primes (generate-primes n primes)]
			(let [div (the-divisors (triangle-number n) gen-primes)]
				(if (>= (count div) number-of-divisors)
					(println "Number:" (triangle-number n) "has" (count div) "divisors")
					(recur (inc n) gen-primes (conj divisors '(n div))))))))


; - BENCHMARK 1 -----------------------
; (time (problem12 50))
; Number:  25200 has  90 divisors
; "Elapsed time: 696.353 msecs"
; -------------------------------------
;
; 	By just adding the "prime-remember" logic we have reduced
; the run time for (problem 50) with 2300 ms.
;	Before we can decide what to do next, we need to do another
; benchmark
;
; - BENCHMARK 2 ------------------------
; (time (the-divisors 500000))
; "Elapsed time: 155491.94 msecs"
; --------------------------------------
;
;	This bencmark tells us that the generation of divisors is way to slow
; when the numbers are getting large. Some of this run time is
; solved by previous generated primes. But probably to slow all in 
; all.
; 	So I'm going to try to optimize the-divisors. I'm starting to wonder if there
; might be a better solution to this problem.
;
; 	Taking a break from this problem.. I'm probably making it way more complicated
; than it has to be..

		