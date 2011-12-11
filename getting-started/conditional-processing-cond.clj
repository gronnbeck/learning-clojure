; cond is a bit different than condp
; cond takes an expression instead of an value..

(print "Enter water temperature in Celsius: ") (flush)
(let [reader (java.io.BufferedReader. *in*)
		line (.readLine reader)
		temperature (try
			(Float/parseFloat line)
			(catch NumberFormatException e line))]
	(println
		(cond
			(instance? String temperature) 	"invalid temperature"
			(<= temperature 0) 				"freezing"
			(>= temperature 100) 			"boiling"
			true							"neither")))
			
