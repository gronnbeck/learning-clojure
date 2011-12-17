; Write a function which replicates each element of a sequence a variable number of times.
; (= (__ [1 2 3] 2) '(1 1 2 2 3 3))

(defn rep [coll x]
	(when-let [[h & t] (seq coll)]
		(concat (repeat x h) (rep t x))))
		
		
(rep [1 2 3] 3)


; as always someone has a shorter solution
;	#(mapcat (partial repeat %2) %)
