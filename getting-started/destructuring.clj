; "Destructuring can be used in the parameter list of a function or macro to extract parts of collections into local bindings. It can also be used in bindings created using the let special form and the binding macro."

; list of vectors and sum its first and third items
(defn approach1 [numbers]
	(let [n1 (first numbers)
		  n3 (nth numbers 2)]
		(+ n1 n3)))
		
(defn approach2 [[n1 _ n3]] (+ n1 n3))

(approach1 [4 5 6 7]) ; -> 10
(approach2 [4 5 6 7]) ; -> 10

; "The ampersand character can be used with destructuring to capturing the remaining items in a collection"

(defn name-summary [[name1 name2 & others]]
	(println (str name1 ", " name2) "and" (count others) "others"))
	
(name-summary ["Moe" "Larry" "Curly" "Shemp"])

; :as can be used to access an entire collection that's being destucted

(defn first-and-third-percentage [[n1 _ n3 :as coll]]
	(/ (+ n1 n3) (apply + coll)))
	
(println (first-and-third-percentage [4 5 6 7]))

