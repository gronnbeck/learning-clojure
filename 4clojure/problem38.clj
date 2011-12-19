; Write a function which takes a variable number of parameters and returns the maximum value.	
; (= (__ 1 8 3 4) 8)

; First try
; (defn m [coll & a]
; 	(println coll)
; 	(println (first a))
; 	(if (nil? a)
; 		(do (println (first coll))
; 			(m (next coll) (first coll)))
; 		(if (> a (first coll))
; 			(m (next coll) a)
; 			(m (next coll) (first coll)))))
; 			
; (m '(1 8 3 4) 1)
;
; Well, I think we can do it in an easier way.
; since the difficulity of this level is EASY.
; (defn m [coll start]
; 	(when-let [[h & t] (seq start)]
; 		(if (reduce (fn [x y] (and x y)) 
; 					true 
; 					(map (fn [x] (>= h x)) coll))
; 			h
; 			(m coll t))))
; must rewrite the #() because they cannot be nested
; whops my mistake. It doesn't take a list for input
; but several of inputs

(defn m [x & xs]
	(reduce #(if (> %2 %1) %2 %1) x xs))

; An elegant solution as well
; #(last (sort %&))
; I want to time them

(def largelist (range 1 10000))
(time (m 1 2 3 4))
(time (#(last (sort %&)) 1 2 3 4))
; "Elapsed time: 0.063 msecs" 
; "Elapsed time: 0.342 msecs"

; as espected the first one is faster than the second.

