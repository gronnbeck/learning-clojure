; Write a function which reverses the interleave process into x number of subsequences.
; (= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))

; Whoop! Whoop! My first medium task. Well to be honest this one looks 
; a lot harder than the other ones. Let's give it a try

(take-nth 2 [1 2 3 4 5 6])
(take-nth 2 [2 3 4 5 6])


; this method can be useful. Now, we have to recursively append this
; method so it creates a n number of lists.

(defn rev-inter [coll x]
	(let [sub (fn sub-seq [coll x n]
		(when-let [c (seq coll)]
			(take-nth x c)
				(filter (fn [y] (= n (count y)))
					(cons (take-nth x c) (sub-seq (next c) x n)))))]	
	(sub coll 
		 x 
		 (/ (count coll) x))))
	
(rev-inter [1 2 3 4 5 6] 2)
(rev-inter (range 9) 3)

; Well that was one big function. Let's us see what the other guys did 
; at this level. 
; 	#(apply map list (partition %2 %))
; That solution was very short compared to mine. 
; This short solution lays in the knowledge of stepwise partitoning
; using the "partition" macro. I must say, that's a very impressive solution.

; -- Clojure API ----------------
; partition
; function
; Usage: (partition n coll)
;        (partition n step coll)
;        (partition n step pad coll)
; Returns a lazy sequence of lists of n items each, at offsets step
; apart. If step is not supplied, defaults to n, i.e. the partitions
; do not overlap. If a pad collection is supplied, use its elements as
; necessary to complete last partition upto n items. In case there are
; not enough padding elements, return a partition with less than n items.
; --------------------------------

