; Write a function which takes a sequence consisting of items with different types and splits them up into a set of homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences themselves can be returned in any order (this is why 'set' is used in the test cases).
; (= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})

; I was first thinking about trying to use the group-by, but then again
; I can find a function that returns the instance name of a data structure / class.
; So I need to rethink.
;	 It may be possible to use this HOP with a smart "validator function". Don't know
; how I'm going to implement it yet though.
;	Found the function! We can use type to group the list. Let's test it

(def group (group-by #(type %) [1 :a 2 :b 3 :c]))
(println "Before filter:" group)
(println "Before filter - set: "(set group))

; looks better. But it still contains the type names, and we don't want that.
; we use a map to remove the type names
(def group-no-typenames (map (fn [x] (second x)) group))
(println "Map filter:" group-no-typenames)
(println "Map filter - set: " (set group-no-typenames))

; Now we need to translate it it for 4clojure
(defn group-and-filter [x]
	(set (map (fn [y] (second y))
		 (group-by (fn [z] (type z)) x))))
		
(println (group-and-filter [1 :a 2 :b 3 :c]))

; From this point I'm going to focus more one how I write my
; Clojure functions. The one above is ugly, and I want to write better,
; more expressive and understandable clojure code.

; and for the more elegan solution
; #(vals (group-by type %))