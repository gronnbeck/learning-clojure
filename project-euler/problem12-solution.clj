; 	I'm tired of spending too much time on problem 12. I know I should 
; keep on trying. So I'm going to look at some others work. 
; I found a solution here http://clojure.roboloco.net/?p=140, and I'm
; too see what he did.
;	As aspected there is a mathematical way of solving this fast.
; The proper page to look for at Wikipedia is 
; http://en.wikipedia.org/wiki/Divisor 
;
;		if the prime factorization of n is given by
;			n = p1^a*p2^b*p3^c	
;		the number of positive divisors of n is
;			d(n) = (a+1)(b+1)(c+1)
;
; 	Roboloco solution is much more elegant than the two page solution a purposed 
; (that doesn't work):

(def triangle-nums (map first (iterate (fn [[n m]] [(+ n m) (+ m 1)]) [1 2])))

(defn prime-factors-of [num]
  "Returns a sorted list of prime factors of num, including multiplicity."
  (let [q (Math/sqrt num)
        factor? (fn [nom den] (zero? (rem nom den)))]
    (loop [n num
           d 2
           r []]
      (cond
       (> d q) (concat r [n])
       (= n d) (concat r [n])
       (factor? n d) (recur (/ n d) d (conj r d))
       true          (recur n (inc d) r)))))

(defn num-divisors-fast [num]
  (let [freqs (reduce #(assoc %1 %2 (inc (get %1 %2 0)))
                      {} (prime-factors-of num))]
    (reduce #(* %1 (inc %2)) 1 (vals freqs))))

(defn euler-12-fast [divisors]
  (first (drop-while #(> divisors (num-divisors-fast %)) triangle-nums)))

(time (println (euler-12-fast 500)))