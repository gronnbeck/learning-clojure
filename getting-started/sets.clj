; "Sets are collections of unique items."
; two kinds: unsorted and sorted

(def stooges (hash-set "Moe" "Larry" "Curly")) 			; not sorted
(def stooges #{"Moe" "Larry" "Curly"})					; same as previous
(def stooges (sorted-set "Moe" "Larry" "Curly"))


(contains? stooges "Moe") 					; -> true
(contains? stooges "Mark")					; -> False

(def more-stooges (conj stooges "Shemp")) 	; -> #{"Moe" "Larry" "Curly" "Shemp"}
(def less-stooges (disj more-stooges "Curly"))
											; -> #{"Moe" "Larry" "Shemp"}
											
; clojure.set namespace: difference, index, intersection, join, map-invert,
; project, rename, rename-keys, select and union