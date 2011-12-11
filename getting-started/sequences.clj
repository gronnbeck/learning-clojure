; Many sequences in clojure are "lazy sequences"
; "Examples of functions that return lazy sequences include: cache-seq, concat, cycle, distinct, drop, drop-last, drop-while, filter, for, interleave, interpose, iterate, lazy-cat, lazy-seq, line-seq, map, partition, range, re-seq, remove, repeat, replicate, take, take-nth, take-while and tree-seq."

(map #(println %) [1 2 3])
; The documentation string for the map function clearly states that it
; returns a lazy sequence

; first, second nth and last forces evaluation of a lazy sequence.
; More importantly, last forces the every items in a list to be evaluated
; ---------------------------------------------------------------------------------------- 
; 						|								|	Discard evaluation results	
;  						|	Retian evaluation results	|	and only uses side effects
; ----------------------------------------------------------------------------------------
; Operate on a single	|	doall						|	dorun
; sequene				|								|
; ----------------------------------------------------------------------------------------
; Operate on any number	|								|
; of sequences with list|	N/A							|	doseq
; comprehension syntax	|								|
; ----------------------------------------------------------------------------------------

; doseq is used more than dorun mostly because of it's readability, and it's also faster..
(dorun 
	(map #(println %) [1 2 3]))

(doseq 
	[i [1 2 3]] (println i))
	
; "If a function creates a lazy sequence that will have side effects when its items are evaluated, in most cases it should force the evaluation of the sequence with doall and return its result."

(doseq [item [1 2 3]] (println item))		; -> nil
(dorun (map #(println %) [1 2 3]))			; -> nil
(doall (map #(do (println %) %) [1 2 3])) 	; -> (1 2 3)

; Ah! Infinite (lazy) sequences

(defn f
	"square the argument and divide by 2"
	[x]
	(println "caluclating f of " x)
	(/ (* x x) 2.0))
	
; infinite (but lazy) sequence of f
(def f-seq (map f (iterate inc 0)))

(println "first is" (first f-seq))

(doall (take 3 f-seq))
(println (nth f-seq 2)) ; -> uses cached result from the doall

; if we don't use doall the items will not be cached thus it might be more
; memory efficient. But may also be less efficient in terms of performance if one
; items is called multiple of times

