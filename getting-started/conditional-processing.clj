; if statements are fairly simple. No deeper explaintion needed
;	(if condition
;		then-expr
;		else-expr)
;
; There are also other useful functions for flow control
; 	(when contidion then-expr else expr)
; 	(when-not condition then-expr else-expr)
; very similar to if.

; if-let binds an variabel based on whether the binding value is true or false
(defn process-next [waiting-line]
	(if-let [name (first waiting-line)]
		(println name "is next")
		(println "no waiting")))
		
(process-next '("Jeremy" "amanda" "Tami")) 	; -> Jeremy is next 
(process-next '())							; -> no waiting

; when-let is similar to if-let except it's a when instead of a if
(defn summarize 
	"prints the first item in a collection
	followed by a period for each remaining item"
	[coll]
	; Execute the when-let body only if the collection isn't empty
	(when-let [head (first coll)]
		(print head)
		; Below, dec substracts one (dec) from
		; the number of items in the collectiom
		(dotimes [_ (dec (count coll))] (print \.))
		(println)))
		
(summarize ["Moe" "Larry" "Curly"])		; -> Moe..
(summarize []) 							; no output

