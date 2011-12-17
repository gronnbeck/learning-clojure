; Write a function which creates a list of all integers in a given range.
; (= (__ 1 4) '(1 2 3))


(defn rang [a b]
	(if (= (inc a) b)
		(list a)
		(cons a (rang (inc a) b))))
		
(println (rang 1 4))


; As always I sum up with another impressive solution
; #(take (- %2 %) (iterate inc %))
; wow that was short.. Have no idea how one can come up with this
; solution. Must have had years of experience with Lisp