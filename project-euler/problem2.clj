; Yeah! Clojure makes it easy to create memoized functions.
; Crossing my fingers that it has hast as memoization should be. 
; Couldn't find my python version of this task, since they all got lost before
; I started using github. So if anyone has a memozied version of this problem
; in python or ruby, please email it to me=)

(defn fibonacci [number]
	(if (= 1 number) 1
	(if (= 2 number) 2
	(+ 
		(fibonacci (- number 1)) 
		(fibonacci (- number 2))))))
	
(def memo-fib (memoize fibonacci))

; Ah! I'm going to try to use iterators since we're going to compute over a million 
; fibonacci numbers (and because I want to learn how to use iterators in Clojure).
; I failed at that for the lat problem. So I guess I have to do some reading this time.

(defn problem2 [max]
	(loop [n 1 sum 0]
		(let [fib (memo-fib n)]
		(if (> fib max)
		sum
		(if (even? fib)
		(recur (inc n) (+ sum fib))
		(recur (inc n) sum))))))

(time (println (problem2 4000000)))

; Well that wasn't too hard...
