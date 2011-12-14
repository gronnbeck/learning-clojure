; metdata (in clojure) is the data attached to a suymbol/collection that is not related to its logcal value.
(defstruct card-struct :rank :suit)
(def card1 (struct card-struct :king :club))
(def card2 (struct card-struct :king :club))

; (println (== card1 card2)) 	; same identity -> false
; line above gets an exception:
; 	java.lang.ClassCastException: 
;		clojure.lang.PersistentStructMap cannot be cast to java.lang.Number
(println (= card2 card2)) 	; equal value -> true

; (def card2 #^{:bent true} card2)		   ; adds metadata at read-time
(def card2 (with-meta card2 {:bent true})) ; adds metadata at run-time
(println (meta card1))		; -> nil
(println (meta card2))		; {:bent true}
(println (= card1 card2))	; still the same value despite different metadata

; Metadata for designated use
;	:private -> boolean that indivated wheter access to a Var is restrictd to a namespace
;	:doc 	 -> is a docoumentation string for a Var
;	:test 	 -> as a boolean that indicates whether a function takes
;			 no arguments is a test function
;	:tag	 -> string that describes the java type of Var or the return type of a function.
;			 Can be useful to find where clojure is using reflection, and thereofr taking a
;			 performance hit, set the global variable *warn-on-reflection* to true
;
; Some Meta data is atuomatically attached to Var by the Clojure compiler
;	:file	 -> the string name of the file that define Var
;	:line	 -> the integer number whitin the file where Var is defined
;	:name	 -> is a Symbok that provides a name for Var
;	:ns		 -> Namespace object that describes the namespace in which Var was defined
;	:macro	 -> a bolean that indicates if Var is a macro oppsed to function or binding
; 	:arglist -> list of vectors where each vector describes the parameters a 
;			 function  accepts
