;	 Refs are used ot ensure that changes to one or more bindings are coordinated between
; multiple threads. Coordinated is done using STM. Refs can only be modified inside
; a transaction
;	STM works like databases, a change is only valid after an transactiom commits.  Code
; that are executed inside a transaction should appear inside a call to the dosync macro.
; The transaction will cast an exception if the transaction isn't made, and roll back
; all the, if there is any, changes
; 	If a transactional conflict appears between to threads. That is, one thread commits 
; a change that another thread is currently working on. The current thread will restart. 
; (It's a optimistic way of solving consistancy problems instead of locking). 
;	Code inside a dosync must be free of side effects. Mutliple reruns may happen, and
; cause unexpected result if they're not idempodent. 
;
; Basic Syntax:
;	Creation of a ref : 				
;		(def name (ref value))
;	
;	Change in-transaction value :
;		(ref-set name new-value) 
;		Condition: inside a dosyc
;
; 	If a the new value is dependent upon the old value. You have to:
;		1. deference the Ref to get the old value
;		2. compute the new value
;		3. set the new value
;
; 	"alter" and "commute" performs these three steps as a single operation. 
;		* alter: changes that must be done in a specific order
;		* commute: where the order is not important (i.e. can be done i parallel)
;
; Syntax for alter and commute:
;	(alter ref fn)
;	(commute ref fn)
;	Condition:
;	 	* inside a dosync
;		* fn must have one paramter where the old value can be passed into
;
; 	the "alter" macro can conflict with other running threads. This one do also restart
; if such a thing will occur. Commmute doesn't do this. 
; 	For each commute call, the ref they set will be reset using the result of the 
; following call:
;		(apply update-function last-commited-value-of-ref args) 
;
; The hands-on example:
(ns com.ociweb.bank)

; Assume the only account data that can change is its balance
(defstruct account-struct :id, :owner, :balance-ref)

; We nee to be able to add and delete accounts to and from a map
; We want it to be sorted so we can easily
; find the higest account number
; for the purpose of assigning the next one
(def account-map-ref (ref (sorted-map)))

(defn open-account
	"creates a new account, stores it in the account map and returns it"
	[owner]
	(dosync ; required becaue a Ref is being changed
		(let [account-map @account-map-ref
			 last-entry (last account-map)
			 ; The id for the neew account is one higher than the last
			 id (if last-entry (inc (key last-entry)) 1)
			 ; Create the new account with a zero starting balance
			 account (struct account-struct id owner (ref 0))]
			; Add the new account to the map of accounts
			(alter account-map-ref assoc id account)
			; Returns the account that was just created
			account)))
			
(defn deposit [account amount]
	"adds money to an account; can be a negative ammount"
	(dosync 
		(Thread/sleep 50) ;sumulate a long-running operation 
		(let [owner (account :owner)
			  balance-ref (account :balance-ref)
			  type (if (pos? amount) "deposit" "withdraw")
			  direction (if (pos? amount) "to" "from")
			  abs-amount (Math/abs amount)]
			(if (>= (+ @balance-ref amount) 0) ; sufficient balance?
				(do 
					(alter balance-ref + amount)
					(println (str type "ing ") abs-amount direction owner))
				(throw (IllegalArgumentException.
					(str "insufficient balance for " owner
						  " to withdraw " abs-amount)))))))
						
(defn withdraw
	"remove money from account"
	 [account amount]
	 ; A withdrawal is like a negative deposit.
	 (deposit account (- amount)))
	
(defn transfer [from-account to-account amount]
	(dosync 
		(println "transferring" amount
				 "from" (from-account :owner)
				 "to" (to-account :owner))
		(withdraw from-account amount)
		(deposit to-account amount)))
		
(defn- report-1	; a prive function
	"prints infomration about a single account"
	[account]
	; This assumes it's being called from within
	; the transaction started in report
	(let [balance-ref (account :balance-ref)]
		(println "balance for" (account :owner) "is" @balance-ref)))
		
(defn report
	"prints information about any number of accounts"
	[& accounts]
	(dosync (doseq [account accounts] (report-1 account))))
	
; Set a defualt uncauht exception handler
; to handle exceptions not caught in other threads
(Thread/setDefaultUncaughtExceptionHandler
	(proxy [Thread$UncaughtExceptionHandler] []
		(uncaughtException [thread throwable]
			; Just print the message in the exception
			(println (.. throwable .getCause .getMessage)))))
			
(let [a1 (open-account "Mark")
	  a2 (open-account "Tami")
	  thread (Thread. #(transfer a1 a2 50))]
	(try
		(deposit a1 100)
		(deposit a2 200)
		
		; There are suffucent founds in Mark's account at this point
		; to transfer 50 to Tami's account
		(.start thread) ; will sleep in deposit function twice! 
		
		; Unfortnunaly, due to the time it takes to compete the transfer,
		; the next call will complete first
		(withdraw a1 75)
		
		; Now there are insufficient funds in Mark's account
		; to complete the transfer
		
		(.join thread) ; wait for thread to finish
		(report a1 a2)
		(catch IllegalArgumentException e
			(println (.getMessage e) "in main thread"))))
			
			

