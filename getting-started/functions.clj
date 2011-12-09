; defining functions in cloujure is easy
(defn hello [name]
	(println "Hello", name))
	
; whops cannot run this yet, haven't installed clojure..
; wait until we're done with the "getting started" section

(hello "Ken")

; whoho! java -jar /Users/ken/Development/api/clojure/clojure.jar functions.clj  runs the script
; I can translate that into a script that runs the script for me as clj functions.clj instead,
; and thats what I'm going to do. these command solves that problem

; echo "alias clj='java -jar /Users/ken/Development/api/clojure/clojure.jar \$1'" >> ~/.bash_profile
; source ~/.bash_profile


; As the tutorial says, this script to run clj program can be more useful:
; 
; This script assumes that java can be found in a directory included in the PATH environment variable. 
; To make this script more useful:
; 
; * Add frequently needed JARs for libraries such as "Clojure Contrib" and database drivers to 
;	the classpath (-cp).
; * Add editing features, completion and cross session command-line recall with rlwrap 
;	(supports vi keystrokes) or JLine.
; * Add the use of a startup script to set special symbols (such as *print-length* and *print-level*), 
;	import commonly used Java classes not in java.lang, load commonly used Clojure functions not in 
;	clojure.core, and define commonly used custom functions.
