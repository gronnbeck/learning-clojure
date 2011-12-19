; Write a function which separates the items of a sequence by an arbitrary value.
; (= (__ 0 [1 2 3]) [1 0 2 0 3])

; I think we can apply mapcat here aswell
(mapcat #(list % 0) [1 2 3]) ; -> [1 0 2 0 3]
; Not exactly what we want but if we add a butlast we're there
(butlast (mapcat #(list % 0) [1 2 3]))
; then we translate for 4clojure
#(butlast (mapcat (fn [x] (list x %1)) %2))

; Or you can use interleave (which we recreated in 39)
; #(rest (interleave (repeat %) %2))