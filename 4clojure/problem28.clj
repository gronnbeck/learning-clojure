(println
	(= ((fn fltn [coll] 
		(let [h (first coll)
			  t (next coll)]
			(concat 
				(if (sequential? h) (fltn h) [h])
				(if (sequential? t) (fltn t))))) 
		'((1 2) 3 [4 [5 6]]))
	'(1 2 3 4 5 6)))