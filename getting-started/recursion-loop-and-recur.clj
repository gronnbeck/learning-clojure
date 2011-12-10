; "The bindings specified by loop provide initial values for the local bindings. Calls to recur cause control to return to the loop and assign new values to its local bindings. The number of arguments passed to recur must match the number of bindings in the loop. Also, recur can only appear as the last call in the loop."

(defn factorial-1 [number]
	"computes the factorial of a positive integer
	in a way that doesn't consume stack space"
	(loop [n number factorial 1]
		(if (zero? n)
		factorial
		(recur (dec n) (* factorial n)))))

(println (time (factorial-1 5)))

; analysis 
;
; (loop [n number factorial 1]) 
; here n is set to number and factorial is set to 1
;
; (recur (dec n) (* factorial n))
; edits the current "state" of the loop and passes it
; to the start of the loop