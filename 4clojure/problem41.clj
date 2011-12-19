; Write a function which drops every Nth item from a sequence.
; (= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])

; Concat list upto nth and list after nth
(drop (- 5 2) [1 2 3 4 5]) 
; so drop gives me the second part of the function
(drop-last 2 [1 2 3 4])
; and drop-last the other part, so this should do the trick
(concat (drop-last (- 8 2) [1 2 3 4 5 6 7 8]) (drop 3 [1 2 3 4 5 6 7 8]))
; and then we need to do this recursivly 
(defn every-nth [n c]
	(when-let [coll (seq c)]
		(concat (drop-last (- (count coll) (- n 1)) coll) 
				(every-nth n (drop n coll)))))
	
(println (every-nth 3 [1 2 3 4 5 6 7 8]))

; the people I follow on 4clojure. Didn't have an anymore elegant solution.
; but I bet that I can do some refactoring to make i prettier