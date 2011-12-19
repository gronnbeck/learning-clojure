; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc. 	
; (= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))

; earlier I discovered this mapcat method. Which
; uses map and then concats the result of the function
; from fn
(mapcat #(list %1 %2) [1 2 3] [:a :b :c])
; it works for lists of the same size what about different sizes?
(mapcat #(list %1 %2) [1 2] [3 4 5 6])
; it stops but the result is what we want.
; 4clojure solution
#(mapcat (fn [x y] (list x y)) %1 %2)
; Another solution, which is about the same, but a shorter
; mapcat list