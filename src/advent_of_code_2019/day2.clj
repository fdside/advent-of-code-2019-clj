(ns advent-of-code-2019.day-2)

(def input (vec (map
                 read-string
                 (clojure.string/split (slurp "resources/day2input.txt") #","))))

(def ops {1 +
          2 *
          99 false})

(defn execute-prog [coll noun verb]
  (loop [tcoll (assoc coll 1 noun 2 verb)
         position 0]
    (let [[op v1 v2 target] (subvec tcoll position (+ position 4))]
      (if (not (ops op))
        (first tcoll)
        (recur (assoc tcoll
                      target
                      ((ops op)
                       (nth tcoll v1)
                       (nth tcoll v2)))
               (+ position 4))))))


(defn part1 []
  (execute-prog input 12 2))

(defn part2 []
  (first
   (for [noun (range 100)
         verb (range 100)
         :when (= 19690720 (execute-prog input noun verb))]
     (+
      (* 100 noun)
      verb))))

(defn -main [& args]
  (println "part1" (part1))
  (println "part2" (part2)))
