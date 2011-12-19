; Write a function which calculates factorials.
; (= (__ 1) 1)

(defn ! [x]
	(if (= x 1)
		1
		(* x (! (- x 1)))))
		
(! 5)

; this is absolutly a cool solution for factorial 
; #(apply * % (range 2 %))