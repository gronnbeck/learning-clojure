(def cols "ABCD")
(def rows (range 1 4)) ; purposely larger than needed to demonstrate :while

(println "for demo")
(dorun
  (for [col cols :when (not= col \B)
        row rows :while (< row 3)]
    (println (str col row))))

(println "\ndoseq demo")
(doseq [col cols :when (not= col \B)
        row rows :while (< row 3)]
  (println (str col row)))	