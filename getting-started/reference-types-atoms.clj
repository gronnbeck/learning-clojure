; "Atoms provide a mechanism for updating a single value that is far simpler than the combination of Refs and STM. They are not affected by transactions."
; 3 function to change the value of an Ataom:
;	1. reset!
;	2. compare-and-set!
;	3. swap!
;
; Syntax
; 	Defining an atom
;		(def name (atom value))
;
;	reset!
;		(reset! atom-name value)
;
; e.g.
(println "reset!")
(def my-atom (atom 1))
(reset! my-atom 2)
(println @my-atom) ; -> 2

; compare-and-set! i usally used at the end of a section of code where the
; beginning is a binding that captures the dereferenced value of the Atom
(println "compare-and-set!")
(reset! my-atom 1) ; set the atom back to 1

(defn update-atom []
	(let [curr-val @my-atom]
		(println "update-atom: curr-val =" curr-val) ; -> 1
		(Thread/sleep 50)
		(println 
			(compare-and-set! my-atom curr-val (inc curr-val))))) ; -> false 

(let [thread (Thread. #(update-atom))]
	(.start thread)
	(Thread/sleep 25) ; give thread time to call update-atom
	(reset! my-atom 3) ; happens after update-atom binds curr-val
	(.join thread)) ; wait for thread to finish
	
(println @my-atom)	; -> 3

; Swap syntax
;	(swap! atom.name fn & [args])
;
; if the compare-and-set! returns false, this function is called repeatedly until
; this check succeds. This is an import feature.
(println "swap!")
(reset! my-atom 1) ; set it back to 1

(defn update-atom-2 [curr-val]
	(println "update-atom: curr-val =" curr-val)
	(Thread/sleep 50)
	(inc curr-val))
	
(let [thread (Thread. #(swap! my-atom update-atom-2))]
	(.start thread)
	(Thread/sleep 25)
	(reset! my-atom 3)
	(.join thread))

(println @my-atom)	; -> 4