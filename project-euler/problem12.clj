; I've never solved this problem before (in any programming language),
; so I'm a bit excited too see if I can do it. #NerdAlert! 

(defn triangle-number [number]
	(loop [i 1 sum 0]
		(if (> i number)
		sum
		(recur (inc i) (+ sum i)))))

(declare prime?)
(declare memo-prime?)

(defn generate-primes [upto]
	(loop [n 3 primes '(2 1)]
		(if (> n (int (Math/ceil (Math/sqrt upto))))
			primes
			(if (memo-prime? n)
				(recur (inc n) (conj primes n))
				(recur (inc n) primes)))))

(defn prime? [n]
	(if (some 
		#(and (= (rem n %) 0) (not (= n %)) (> % 1)) 
		(generate-primes n))
	false
	true))
(def memo-prime? (memoize prime?))

; For this to be efficient. You should always check that number
; is a factorial prime of in
(defn factors-of? [number in]
	(if (= 1 number)
	1
	(int (Math/floor (/ in number)))))

(defn possible-divs [prime number divisors]
	(loop [n 1 div divisors]
	(if (= n (factors-of? prime number))
		(if (= 0 (rem number (* prime n)))
			(conj div (* prime n))
			div)
		(if (= 0 (rem number (* prime n)))
			(recur (inc n) (conj div (* prime n)))
			(recur (inc n) div)))))
	
; (println (factors-of? 2 4))
; (println (possible-divs 2 4))

(defn number-of-divisors [number]
	(loop [primes (generate-primes (* number number)) divisors #{}]
		(if (= 0 (count primes))
			(count (conj divisors number))
			(if (= 0 (rem number (first primes)))
				(recur (next primes) (possible-divs (first primes) number divisors))
				(recur (next primes) divisors)))))
	
; (time (println "1: " (number-of-divisors 1)))
; (time (println "3: " (number-of-divisors 3)))
; (time (println "6: " (number-of-divisors 6)))
; (time (println "10: " (number-of-divisors 10)))
; (time (println "15: " (number-of-divisors 15)))
	
	
(defn problem12 [divisors]
	(loop [n 1]
		; (println n " trinalge number is" (triangle-number n))
		(let [div (number-of-divisors (triangle-number n))]
		(if (>= div divisors)
			(println "Number: " (triangle-number n) "has " div "divisors")
			(recur (inc n))))))
		
(time (problem12 500))


;	 Lookin at the Data below the run for 500 is going to take a lot
; of time. However, I will try to run it and see if it terminates
; within 2-4 hours. 
;	This solution clearly isn't a optimized solution. I think it's too
; early in the learning curve too learn optimization of Clojure. 
; I will get back to this problem later on.. When I possibly need to learn
; more about program more optimized code with Clojure.
;	Nope it ran out of heap space.. Optimization needed

; 50
; Number:  25200 has  90 divisors
; "Elapsed time: 3059.85 msecs"

; 91 
; Number:  73920 has  112 divisors
; "Elapsed time: 9557.47 msecs"

; 113
; Number:  157080 has  128 divisors
; "Elapsed time: 28797.883 msecs"