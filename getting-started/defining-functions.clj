(defn parting
	"returns a String parting"
	[name]
	(str "Goodbye, " name))

(println (parting "Ken"))

; to bypass the create-method-before-use use:
(declare function-name)

; defn- macro are private – only visible in the same namespace

(defn power [base & exponents]
	; Using java.lang.Math static method pow
	(reduce #(Math/pow %1 %2) base exponents))
(println (power 2 3 4)) 
; 2 to the 3rd = 8 
; 8 to the 4th = 4096

(defn parting
	"returns a String parting in a given language"
	([] (parting "World"))
	([name] (parting name "en"))
	([name language]
		; condp is similar to a case statement in other languages.
		; It is described in more detail later
		; It is used here to take different actions based on wheter the
		; paramter "language" is se to "en", "es" or something else
		(condp = language
			"en" (str "Goodbye, " name)
			"es" (str "Adios, " name)
			(throw (IllegalArgumentException.
				(str "unsupported language " language))))))
				
(println (parting))		; -> Goodbye, World
(println (parting "Mark"))
(println (parting "Mark", "es"))
(println (parting "Mark", "en"))
(println (parting "Mark", "xy"))

; anonymous function have no name (and they're awesome).
(def years [1940 1944 1961 1985 1987])
(println (filter (fn [year] (even? year)) years))	; long w/ named arguments
(println (filter #(even? %) years))					; short where % refers to argument

; anonymous functions with multiple expressions
(defn pair-test [test-fn n1 n2]
	(if (test-fn n1 n2) "pass" "fail"))

; Use a test-fn that determines wheter the sum of its two arguments is an even number
(println (pair-test #(even? (+ %1 %2)) 3 5)) ; -> pass

; "Java methods can be overloaded based on parameter types. Clojure functions can only be overloaded on arity. Clojure multimethods however, can be overloaded based on anything."

(defmulti what-am-i class)			; class is the dispatch function
(defmethod what-am-i Number [arg] (println arg "is a Number"))
(defmethod what-am-i String [arg] (println arg "is a String"))
(defmethod what-am-i :default [arg] (println arg "is something else"))
(what-am-i 19)
(what-am-i "Hello")
(what-am-i true)

; use of underscore (unnamed variable) in callback functions
(defn callback1 [n1 n2 n3] (+ n1 n2 n3))
(defn callback2 [n1 _ n3] (+ n1 n3))
(defn caller [callback value]
	(callback (+ value 1) (+ value 2) (+ value 3)))
(caller callback1 10) ; -> 11 + 12 + 13 -> 36
(caller callback2 10) ; -> 11 + 13 -> 24

(defn teenager? [age] (and (>= age 13) (< age 20)))
(def non-teen? (complement teenager?))
(println (non-teen? 47))	; -> true


; comb combines any # of existing function into a new one (from rigth to left)
(defn times2 [n] (* n 2))
(defn minus3 [n] (- n 3))
; Note the use of def instead of defn because comp returns a function that is then bound to "my-composition"
(def my-composition (comp minus3 times2))
(println (my-composition 4)) ; -> 4*2 -3 -> 5


; "partial" – don't quite understand how it works
; Need to read more about it later
(def times2 (partial * 2))
(println (times2 3 4)) ; 2 * 3 * 4 -> 24 


(defn- polynomial
	"computes the value of a polonymial
	with the given coefficients for a given value x"
	[coefs x]
	; For example, if coefs contains 3 values then exponents is (2 1 0)
	(let [exponents (reverse (range (count coefs)))]
		; Multiply each coefficient by x raised to the corresponding exponent
		; and sum those results.
		; coefs go into %1 and exponents go into %2
		(apply + (map #(* %1 (Math/pow x %2)) coefs exponents))))

(defn- derivative
	"computes the value of the derivative of a polynomial
	with the given coefficints for a given value x"
	[coefs x]
	; The coefficients of the derivative function are obtained by
	; multiplying all but the last coefficient by its corresponding exponent.
	; The extra exponent will be ignored
	(let [exponents (reverse (range (count coefs)))
			derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
			(polynomial derivative-coefs x)))

(def f (partial polynomial [2 1 3])) ; 2x^2 + x + 3
(def f-prime (partial derivative [2 1 3])) ; 4x + 1

(println "f(2) = " (f 2))			; -> 13
(println "f'(2) = " (f-prime 2))	; -> 9


; Another way to implement the polynomial function
(defn- polynomial
	"computes tje value of a polynomial
	with the given coefficients for a given value"
	[ceofs x]
	(reduce #(+ (* x %1) %2) coefs))


; "The memoize function takes another function and returns a new function that stores a mapping from previous arguments to previous results for the given function."

; "The time macro evaluates an expression, prints the elapsed time, and returns the expression result. It is used in the following code to measure the time to compute the value of a polynomial at a given x value."

(def memo-f (memoize f))
(println "priming call")
(time (f 2))

(println "without memoization")
(dotimes [_ 3] (time (f 2)))

(println "with memoization")
(dotimes [_ 3] (time (memo-f 2)))


