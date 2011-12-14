; Reference types are mutable refernces of immutable data
; Four reference types:
;	vars, refs, atoms, agents
; Commonalitites:
;  * can hold any kind of object
;  * can be dereferenced to retreive the object they hold with the "deref" function
;	or the @ reader macro
;  * support validators which are functions that're invoked when the value changes
;  * support watchers (agents). when a watched value changes an agent is notified

; Vars 		are references that can have a root binding that is shared by all threads, 
;			and can have a different value in each thread (thread-local)
; Syntax: 
;	(def name value) 
; 	* value is optional
; Syntax for creating thread-local bindings of exisiting var:
; 	(binding [name expression] body)
;	or (set! name expression) ; inside a binding that bound the same name
; e.g.

(def ^:dynamic v 1)

(defn change-it []
	(println "2) v =" v)	 	; -> 1
	
	(def v 2)					; changes the root value
	(println "3) v =" v)		; -> 2
	
	(binding [v 3]				; binds a thread-local value
		(println "4) v =" v)	; -> 3
		
		(set! v 4)				; changes thread-local value
		(println "5) v =" v))	; -> 4
	
	(println "6) v =" v))		; thread local value is gone -> 2

(println "1) v =" v)			; -> 1

(let [thread (Thread. #(change-it))]
	(.start thread)
	(.join thread))				; wait for thread to finish
	
(println "7) v =" v)			; -> 2

; Well.. That didn't work 
; 	Exception:
;	"Thread-1" java.lang.IllegalStateException: Can't dynamically bind non-dynamic var: user/v
; Looks like it fails at (binding [v 3] ...) Wonder why? Maybe never version of clojure doesn't
; allow such bindings?
; 	OK, Solved it! Had to mark the variable v as ^:dynamic... Don't know if this is the correct
; way to do it or not. The output is correct so I'll assume that this is the way to do it!
