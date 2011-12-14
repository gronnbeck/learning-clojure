; Validation function (:validator)

; Note the use of the :validator directive when creating the Ref
; to assing a validation function which is integer? in this case
(def my-ref (ref 0 :validator integer?))

(try 
	(dosync 
		(ref-set my-ref 1) ; should work
		
		; The next line doesn't work so the transaction is rolled back
		; and the previous changes isn't commited
		(ref-set my-ref "foo"))
	(catch IllegalStateException e
		; do nothing
		))
		
(println "myref =" @my-ref) ; due to validation failure ->  0