; Wow I used over an hour on this task, which would have taken less than 5 minutes
; in ruby, python or any other scriptable OO-languages. I had totaly forgot how 
; difficult it was to learn how to program. However, good practice with a functonal
; programming language with bindings! My mind is not used to think outside of the
; OO paradigm!

(defn legal? [number]
	(or(= (rem number 5) 0) (=(rem number 3) 0)))
	
; (legal? 5)
(defn problem1-recursive 
	"recursivly computes the first task of the eulerproject"
	[start end]
	(if (= start end)
		start
	(if (legal? start)
		(+ start (problem1-recursive (inc start) end))
		(problem1-recursive (inc start) end))))

	

(problem1-recursive 0 999)	