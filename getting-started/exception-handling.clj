; as in Java – try, catch, finally and throw

(defn collection? [obj]
	(println "obj is a" (class obj))
	(or (coll? obj)
		(instance? java.util.Collection obj)))
		
(defn average [coll]
	(when-not (collection? coll)
		(throw (IllegalArgumentException. "exepcted a collection")))
	(when (empty? coll)
		(throw (IllegalArgumentException. "collection is empty")))
	(let [sum (apply + coll)]
		(/ sum (count coll))))
		
(try
	(println "list average =" (average '(2 3)))
	(println "vector average = "(average [2 3]))
	(println "set average = " (average #{2 3}))
	(let [al (java.util.ArrayList.)]
		(doto al (.add 2) (.add 3))
		(println "ArrayList average = " (average al)))
	(println "string average = "(average "1 2 3 4"))
	(catch IllegalArgumentException e
		(println e))
	(finally 
		(println "in finally")))