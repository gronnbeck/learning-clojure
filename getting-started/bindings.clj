; def
; "def" creates a global binding and optionally gives it a "root value", this means that the
; binding is visible in all threads unless specified otherwise. "def" can be used to change
; root value of existing binding, but doing so is frwned upon..

; let
; "let" lets you create local bindings.
; "They can also be assigned to more than once to change their value." <-- is it mutable?

; binding
; this macro is imilar to "let", but it temporarily gives new, thread-local values to existing 
; global bindings. The new values are seen inside that form and also in functions called from inside it. 
; When the binding form exits, the bindings revert to their previous values.

; Another difference is that "let" assigns values sequentially, allowing values to be based on previously
; set bindings, whereas "binding" assigns values in parallel.

(def ^:dynamic v 1)								; v is a global binding

(defn f1 []
	(println "f1: v =" v))						; global binding
	
(defn f2 []
	(println "f2: before let v =" v)			; global binding
	(let [v 2]									; creates a local binding v that shadows the global one
		(println "f2: in let, v =" v)			; local binding
		(f1))
	(println "f2: after let v =" v))			; global binding
	
(defn f3 []
	(println "f3: before binding v =" v)		; global binding
	(binding [v 3]								; same global binding with, temporary value
		(println "f3: after binding v =" v)		; global binding
		(f1))
	(println "f3: after binding v =" v))		; global binding
	
(defn f4 []
	(def v 4))									; changes the value of the global binding
	
(f2)
(f3)
(f4)
(println "after calling f4, v =" v)