; some way of creating lists
(def stooges (list "Moe" "Larray" "Curly"))
(def stooges (quote "Moe" "Larray" "Curly"))
(def stooges '("Moe" "Larray" "Curly"))

(some #(= % "Moe") stooges)					; -> true
(some #(= % "Mark") stooges)				; -> nil

(contains? (set stooges) "Moe") 			; -> true
; is the last operation faster? Since sets are better for
; searching for items? 

(def more-stooges (conj stooges "Shemp")) 	; -> ("Shemp" "Moe" "Larry" "Curly")
(def less-stooges (remove #(= % "Curly") more-stooges))
											; -> ("Shemp", "Moe", "Larry")

(def kids-of-mike '("Greg" "Peter" "Bobby"))
(def kids-of-carol '("Marcia" "Jan" "Cindy"))
(def brady-bunch (into kids-of-mike kids-of-carol))
(println brady-bunch)
; Ah! Cannot believe I didn't to this before: 
; Mark the code you want to run and press CMD+SHIFT+X 

; Other operations: peek and pop 

