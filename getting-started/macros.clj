; macros ar euse dto add new constructs to the language
; To deterimine if an option as a macro or a function type (doc name) or check the macro
((meta (var and)) :macro)		; -> true
; (^#'and :macro)					; short way -> true
; Exception: Metadata must be Symbol,Keyword,String or Map
;	Look into it later

; use defmacro to define macros
(defmacro around-zero [number negative-expr zero-expr positive-expr]
	`(let [number# ~number]	; so number is only evaluated once
		(cond
			(< (Math/abs number#) 1e-15) ~zero-expr
			(pos? number#) ~positive-expr
			true ~negative-expr)))
			
; - This concept was a little hard to grasp  -----------------------------------------
; 	number# is a used to generate a unique symbol name so there is
; 			no chance the binding name can conflict with another symbol.
;			This enables "hygenic macros": http://en.wikipedia.org/wiki/Hygienic_macros
;	` (the back quoute) prevents everything inside from being evaluted unless it is 
;	unquoted. This means the contents will appear literally in the expansion.
;	Items that are preceded by tilde ("~") will be substiuted inside a syntax quoted list
;	Bindings in syntax quoted lists whose values are sequences can be proceded by
;	"~@" to subsitute their indivudal items.

(around-zero 0.1 (println "-") (println "0") (println "+"))
(println (around-zero 0.1 "-" "0" "+"))

; To execute more than one command for one of the cases. 
; Just wrap them inside a (do expr)
(around-zero -0.1 
	(do (println "Cooold") (println "Yeah Really Cold -"))
	(println "0")
	(println "+"))
	
; to verify macro is expaneded properly
(println (macroexpand-1 '(around-zero 0.1 (println "-") (println "0") (println "+"))))

; macro that takes to arguments.
;	first  : a function that expects one argument
;	second : is a number 

(defamcro trig-y-category [fn degrees]
	`(let [radians# (Math/toRadians ~degrees)
		   result# (~fn radians#)]
		(number-category result#)))


; macros cannot be passed as functions in a method. 
; This is becuase Macro calls are processed at read-time.
; You can do a work around for this and put the macro inside an anonymous function #()

