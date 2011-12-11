; condp works like case statements in other languages such as Java
(print "Enter a number: ") (flush)
(let [reader (java.io.BufferedReader. *in*)
		line (.readLine reader)
		value (try
				(Integer/parseInt line)
				(catch NumberFormatException e line))]
	(println
		(condp = value
			1 "one"
			2 "two"
			3 "three"
			(str "unexpected value, \"" value \")))
	(println
		(condp instance? value
			Number (* value 2)
			String (* (count value) 2))))
			
