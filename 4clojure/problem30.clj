; (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
; (apply str
; 	(map #(do %) "Heeellooo"))

; (map (fn [x] (if (= x (char \H)) "$" x)) (map #(do %) "Heeellooo"))

; My 4th attempt.. Wasn't going anywhere
; (apply str
; 	(loop [s (map #(do %) "Heeellooo") 
; 		   r '()]
; 		(println "s =" s)
; 		(if (nil? (first s))
; 			(println "r =" r)
; 			(recur (next s) (concat r [(first s)])))))

; Stuck for too long.. Googling
; Ah that was an elegant solution.. It's hard to not notice
; how little use recursion. However, this technique might be prone to
; overflowing the stack. Since it uses recursion instead of iterations.
; Growing vs constant stack
(defn compress [coll]
	(when-let [[h & t] (seq coll)]
		(if (= h (first t))
			(compress t)
			(cons h (compress t)))))
			
			
(println (apply str (compress "Heeeellooooo")))