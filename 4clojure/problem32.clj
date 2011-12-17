; Write a function which duplicates each element of a sequence.	
; (= (__ [1 2 3]) '(1 1 2 2 3 3))
; (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

; Almost got it with this one
; (map (fn [x] (repeat 2 x)) [1 2 3]) 
;	-> ((1 1) (2 2) (3 3))

; Doesn't work for the nested lists
; (flatten (map (fn [x] (repeat 2 x)) [[1 2] [3 4]]))

; Close but no cigar..
(defn dup [coll]
	(when-let [[h & t] (seq coll)]
		(concat (list h h) (dup t))))
		
; Doing by hand worked this time.

; (dup [1 2 3])
; (dup [[1 2] [3 4]])

; Another solution 
;	mapcat #(list % %)
; this one is elegant and I would say "hacky" becuse I have no
; idea of what is going on behind that line of code. 
; I should look into what _mapcat_ does

