; Write a higher-order function which flips the order of the arguments of an input function.
; (= 3 ((__ nth) 2 [1 2 3 4 5]))

(defn flip [f]
	(fn [x xs] (f xs x)))


((flip nth) 2 [1 2 4 5 6])