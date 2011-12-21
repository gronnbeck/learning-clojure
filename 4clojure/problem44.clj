; Write a function which can rotate a sequence in either direction.
; (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
; (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))

(concat (drop 2 [1 2 3 4 5]) (take 2 [1 2 3 4 5]))
(concat (take-last 2 [1 2 3 4 5]) (drop-last 2 [1 2 3 4 5]))

(defn rotate1 [nm coll]
	(let [n (Math/abs nm)]
		(if (< nm 0)
			(concat (take-last n coll) (drop-last n coll))
			(concat (drop n coll) (take n coll)))))
	
(println (rotate 2 [1 2 3 4 5])
		 (rotate -2 [1 2 3 4 5]))
		

; Seems we have a problem. We need to "rephrase" the value
; of how much the sequence should rotate such that it always
; satisfies this condition:
;	nm <= |length of nm|
; we do the changes to n, because it's only value of nm that is
; ever used

(= 1 (rem (Math/abs 6) (count [1 2 3 4 5]))) ; -> true
(= 1 (rem (Math/abs -6) (count [1 2 3 4 5]))) ; -> true
(defn rotate [nm coll]
	(let [n (rem (Math/abs nm) (count coll))]
		(if (< nm 0)
			(concat (take-last n coll) (drop-last n coll))
			(concat (drop n coll) (take n coll)))))
			
(println (rotate 6 [1 2 3 4 5]))

; that worked. Seems that we didn't have to to the abs thing.
; #(let [c (count %2) p (mod % c)]
;    (concat (drop p %2) (take p %2)))
; was another solution. hmm...