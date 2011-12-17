; Write a function which packs consecutive duplicates into sub-lists.
; (= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))

; Grasping the problem and my first attempt
; (defn pack [coll]
; 	(when-let [[h & t] (seq coll)]
; 		(if (= h (first t))
; 			; if equal
; 			; 	Recurisvly add similar numbers to the sub-list
; 			(cons h (pack t))
; 			; if not
; 			; 	Add the sub-list to the list, and recursivly add new sub-lists
; 			(cons (list h) (pack t)))))
; 			
; (println (pack [1 1 2])) 

; second and working attempt. As simple as that
(partition-by #(do %) [1 1 2])

; Clojure can be effecivie if you know it.
; I kind of wanted to solve the problem with my own algorithm,
; maybe next time...