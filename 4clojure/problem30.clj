; (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")

(apply str
	(map #(do %) "Heeellooo"))
			


; (map (fn [x] (if (= x (char \H)) "$" x)) (map #(do %) "Heeellooo"))
(reduce (fn colli [x] x) "heeello")
