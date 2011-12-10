; "All Clojure functions implement both the java.lang.Runnable interface and the java.util.concurrent.Callable interface. This makes it easy to execute them in new Java threads."

(defn delayed-print [ms text]
	(Thread/sleep ms)
	(println text))
	
(.start (Thread. #(delayed-print 1000 ", World!"))) 
(print "Hello")

; Hmm! The syntax for starting threads isn't that pretty...
; Hopefully, they will improve that in the future..
