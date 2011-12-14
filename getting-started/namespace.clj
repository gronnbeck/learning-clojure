; use the ns macro to change the default name space. 
; the standard "user" namespace provides access to all symbols in clojure.core

; to access items that is not inside of a default namespace. 
; we can use (require namespace). e.g.
(require 'clojure.string)
; where clojure.string is a namespace containing symbols not in clojure.core

; to use the sumbols in a different or custom namespace
; we use (namespace/symbol paramter-list). It's just like calling a javaobj
(clojure.string/join "$" [1 2 3]) ; -> "1$2$3"

; use aliases to ease the use of symbols in other namespaces
(alias 'su 'clojure.string)
(su/join "$" [1 2 3]) ; is the same join-call as above

; refer – makes all symbols accessibale in the current namespace e.g.
(refer 'clojure.string)
(join "$" [1 2 3]) ; I think you know the deal is

; use – is a shortcut for using require and refer
(use 'clojure.string)
; Was the use function introduced, because inorder to refer we have to require first?

; the ns macro – changes the default namespace
;	supports	:require, :use, :import (java classes)
;	:as			for creating aliases for a namespace
;	:only		to load parts of the library
; example
(ns com.ociweb.demo			
	(:require [clojure.string :as su])
	; assumes this dependency: [org.clojure/math.numeric-tower "0.0.1"]
	(:use [clojure.math.numeric-tower :only (gcd, sqrt)])
	(:import (java.text NumberFormat) (javax.swing JFrame JLabel)))
	
(println (su/join "$" [1 2 3]))
(println (gcd 27 72))
(println (sqrt 5))
(println (.format (NumberFormat/getInstance) Math/PI))
; something wwrong with numeric-tower.. So I'm not able to test it for now

; create-ns – creates a new namespace but doesn't make it the default
; def – defines a sumbol in the default namespace with an optional inital value
; intern – defines a symbol in a given namespace 
(def foo 1)
(create-ns 'com.ociweb.demo)
(intern 'com.ociweb.demo 'foo 2)
(println (+ foo com.ociweb.demo/foo))

; ns-interns – returns a map of all smbols in the currently loaded namespace
; all-ns – returns a sequence of the currently loaded namespaces
; other related namespace functions:
;	ns.aliases, ns-imports, ns-map, ns-name, ns-publics
;	ns-refers, ns-unalias, ns-unmap, and remove-ns

 