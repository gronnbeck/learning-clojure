; Association between keys and corresponding values

(def popsicle-map
	(hash-map :red :cherry, :green :apple, :purple :grape))
(def popsicle-map
	{:red :cherry, :green :apple, :purple :grape})
(def popsicle-map
	(sorted-map :red :cherry, :green :apple, :purple :grape))

; maps are function of keys
; in some cases keys can be used as functions of maps

(get popsicle-map :green)			; -> :apple
(popsicle-map :green)				; -> :apple
(:green popsicle-map)				; -> :apple

(contains? popsicle-map :green)		; -> true
(keys popsicle-map)					; -> (:red :green :purple)
(vals popsicle-map)					; -> (:cherry :apple :grape)

(assoc popsicle-map :green :lime :blue :blueberry)
; -> {:blue :blueberry, :green :lime, :purple :grape, :red :cherry}

(dissoc popsicle-map :green :blue) 	; -> {:purple :grape, :red :cherry}

; "When used in the context of a sequence, maps are treated like a sequence of 	clojure.lang.MapEntry objects. This can be combined with the use of doseq and destructuring, both of which are described in more detail later, to easily iterate through all the keys and values. "

(def popsicle-map
	{:red :cherry, :green :apple, :purple :grape})
(doseq [[color flavor] popsicle-map]
	(println (str "The flavor of " (name color) 
		" popsicles is " (name flavor) ".")))

(select-keys popsicle-map [:red :green :blue])  ; -> {:green :apple, :red :cherry}

; using "conj" will add all key/value pairs from one map to another.
; existing keys will be overwritten 

; maps can be nested to create a datastructure 
(def person {
	:name "Mark Volkmann"
	:address {
		:street "644 Glen Summit"
		:city "St. Charles"
		:state "Missouri"
		:zip 63304}
	:employer {
	:name "Object Computing, Inc."
	:address {
	:street "12140 Woodcrest Executive Drive, Suite 250"	
	:city "Creve Coeur"
	:state "Missouri"
	:zip 63141}}})
	
(get-in person [:employer :address :city])
(-> person :employer :address :city)
(reduce get person [:employer :address :city])

; the -> explained: is a thread that calls a series of function, passing the result of each as the argument to the next

(f1 (f2 (f3 x)))
; is the same as
(-> x f3 f2 f1)

; there is also a macro "-?>"that returns nil if any of the functions returns nil
; this is to avoid NullPointerExceptions

; reduce is a fold left operation:
; "The reduce function takes a function of two arguments, an optional value and a collection. It begins by calling the function with either the value and the first item in the collection or the first two items in the collection if the value is omitted. It then calls the function repeatedly with the previous function result and the next item in the collection until every item in the collection has been processed."

(assoc-in person [:employer :address :city] "Clayton") 
; replaces "Creve Coeur" with "Clayton"

(update-in person [:employer :address :zip] str "-1234")

; there is much to learn here... Especially the commands such as macro and reduce. They can be increadably useful if they're understood and can be applied without using much brainpower.
