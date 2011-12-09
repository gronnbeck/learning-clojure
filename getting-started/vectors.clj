; vectors! Don't we just love them
(def stooges (vector "Moe" "Larry" "Curly"))
(def stooges ["Moe" "Larry" "Curly"])

(get stooges 1 "unknwon") ; -> "Larry"
(get stooges 3 "unknown") ; because 3 is out of bounds -> "unknown"
; in other words if the index is out of range it returns the third input "unknown"

(assoc stooges 2 "Shemp") ; -> ["Moe" "Larry" "Shemp"]

; subvec returns a new vector which is a subset of an existing one that reains the
; order of the items