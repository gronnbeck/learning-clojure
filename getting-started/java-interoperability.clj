; import packages from java
(import
	'(java.util Calender GregorianCalender)
	'(javax.swing JFrame JLabel))
	
; two ways of accessing constants in javaclasses
(. java.util.Calender APRIL) ; -> 3
(. Calender APRIl) ; works if Calender is imported
java.util.Calender/APRIL
Calender/APRIL ; works if the calender was imported

; invoking methods
(. Math pow 2 4)	; -> 16
(Math/pow 2 4)

; invoking constructurs 
(import '(java.util Calendar GregorianCalendar))
(def calendar (new Gregorian 2008 Calendar/APRIL 16))	; April 16, 2008
(def calender (GregorianCalendar. 2008 Calendar/APRIL 16))

; Two ways invoke an instance method on a Java Object
(. calendar add Calendar/MONTH 2)
(. calendar get Calendar/MONTH)	; -> 5
(.add calendar Calendar/MONTH 2)
(.get calendar Calendar/MONTH)	; -> 7


; long and short way to chain methods
(. (. calendar getTimeZone) getDisplayName)
; vs
(.. calendar getTimeZone getDisplayName) ; -> "Central Standard Time"
; u can also use .?. to avoid NullPointerException

; The doto function is used to invoke many methods on the same object.
(doto calendar
	(.set Calendar/YEAR 1981)
	(.set Calendar/MONTH Calendar/AUGUST)
	(.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(.format formatter (.getTime Calendar)) ; -> "Aug 1, 1981"