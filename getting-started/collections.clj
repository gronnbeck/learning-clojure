; All of collections in Clojure are immutable, heterogeneous and persistent
; * Being persistent means that old versions of them are preserved when new 
;	versions are created.

(count [19 "yellow" true]) 				; -> 3

(reverse [2 4 7])						; -> (7 4 2)

; uses an anonymous function that adds 3 to its argument
(map #(+ % 3) [2 4 7])					; -> (5 7 10)

(map + [2 4 7] [5 6] [1 2 3 4]) 		; adds corresponding items -> (8 12)
; hmm what is exactly happening here?
; For now I hypthesize that what is does is
;	2 + 5 + 1 = 8,
; 	4 + 6 + 2 = 12,
; and ignores the rest because the smallest list [5 6] has total length of 2

(apply + [2 4 7])						; -> 13

(def stooges ["Moe" "Larry" "Curly" "Shemp"])
(first stooges)							; -> "Moe"
(second stooges)						; -> "Larry"
(last stooges)							; -> "Shemp"
(nth stooges 2)							; -> "Curly" (0 indexed)

(next stooges)							; -> ("Larry" "Curly" "Shemp")
(butlast stooges)						; -> ("Moe" "Larry" "Curly")
(drop-last 2 stooges)					; -> ("Moe" "Larry")
; names containing more than 3 characters
(filter #(> (count %) 3) stooges)		; -> ("Larry" "Curly" "Shemp") 
(nthnext stooges 2)						; -> ("Curly" "Shemp")

(every? #(instance? String %) stooges)	; -> true
(not-every? 
	#(instance? String % stooges))		; -> false
	
(some #(instance? Number %))			; -> nil
(not-any? 
	#(instance? Number % stooges))		; -> true