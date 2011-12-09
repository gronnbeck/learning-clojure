; This is a Clojure code
; When a set is used as afunction, it returns a boolean
; that indicates whether the argument is in the set

(def vowel? (set "aeiou"))

(defn pig-latin [word]									; defines a functiom
	(let [first-letter (first word)]					; assings a local binding
		(if (vowel? first-letter)						
			(str word "ay")								; then part of if
			(str (subs word 1) first-letter "ay"))))	; else part of if
			
(println (pig-latin "red"))
(println (pig-latin "orange"))