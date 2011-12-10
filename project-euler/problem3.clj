; Well! It turns out that this is a bad solution.. 
; Because of memo-prime? we will eventually run out of java heap space,
; which is kind of insane. 
;
; I know we can replace the prime generator with a more efficient one,
; and there will be no problem. But I leave that for later.
;
; Made a few changes to the program reduced the example program from 2.5 s
; to 0.015 s so that's a performance increase. Let's see if it passes now
; or if I have to use a more sophisticated prime generator...

(declare prime?)
(declare memo-prime?)

(defn generate-primes [upto]
	(loop [n 3 primes '(1 2)]
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

(defn problem3 [number]
	"Now I only have to figure this part out"
	(loop [primes (generate-primes number)]
		(if (= 0 (rem number (first primes)))
		(first primes)
		(recur (next primes)))))
		
		
(time (println (problem3 600851475143)))

; It worked! 
; -> 6857
; -> "Elapsed time: 337206.045 msecs"
; But it crashed right after this
;	"Exception in thread "main" java.lang.RuntimeException: Unable to resolve symbol: out in this context, compiling:(/Users/ken/Development/learning-clojure/project-euler/problem3.clj:0)"
; Dunno what that is but I'll solve that problem later if it appears again..

