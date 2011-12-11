(binding [*out* (java.io.FileWriter. "my.log")]
	;...
	(println "This goes to the file my.log.")
	;...
	(flush))
	
; println flushes since *flush-on-newline* is set to true

; "The pr and prn functions are like their print and println counterparts, but their output is in a form that can be read by the Clojure reader. They are suitable for serializing Clojure data structures. By default, they do not print metadata. This can be changed by binding the special symbol *print-meta* to true."

(let [obj1 "foo"
	  obj2 {:letter \a :number (Math/PI)}]
	(println "Output from print:")
	(print obj1 obj2)
	
	(println "Output from println:")
	(println obj1 obj2)
	
	(println "Output from pr:")
	(pr obj1 obj2)
	
	(println "Output from prn")
	(prn obj1 obj2))

; this is only a few IO options you could work with.
; Remember that the Java IO library is very powerful, so many useful
; classes can be used for different types of IO's in clojure