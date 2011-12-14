; "Agents are used to run tasks in separate threads that typically don't require coordination. They are useful for modifying the state of a single object which is the value of the agent."
; * Only one action at a time will be run on a given Agent *
;
; Syntax
;	(def my-agent (agent inital-value))
;
; send – dispatches an action to an Agent and reutnrs immediately. The action is 
; 		executed in a thread from a supplied thread pool.
; send-off – similar but uses threads from a different pool
;
; 	"send" uses a "fixed thread pool" (newFixedThreadPool) where the number of threads 
; is the number of processors plus 2
;	"send-off" uses a "cached thread pool" (newCachedThreadPool) where existing threads
; in the pool are used f available and new threads are added otherwise
;	  
; 	If send or send-off is called within an transaction. The action isn't really sent 
; until the transaction has commited. 
;
; await – takes any number of Aganets and blocks the current thread until all
; 		actions that thread has dispatched to those Agents have completed
; await-for – similar but takes a timeout in ms as its first argument
; agent-errors – retreive all exception thrown in all actions to a given agent
; shutdown-agents – waits for the execution of alla ctions already sent to all
;		agents to complete. Then it stops all the threads in the thread pool that
;		are used by Agents. This command is necessary to be bale to exit the JVM
;		in an orderly manner.


