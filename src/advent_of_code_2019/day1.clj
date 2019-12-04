(ns advent-of-code-2019.day1)

(def input (map
            read-string
            (clojure.string/split-lines (slurp "resources/day1input.txt"))))

(defn calculate-fuel [mass]
  (- (int (/ mass 3)) 2))

(defn calculate-fuel-rec [mass]
  (rest (take-while pos? (iterate calculate-fuel mass))))

(defn part1 []
  (->> input
       (map calculate-fuel)
       (reduce + 0)))

(defn part2 []
  (->> input
       (mapcat calculate-fuel-rec)
       (reduce + 0)))

(defn -main [& args]
  (println "part1" (part1))
  (println "part2" (part2)))
