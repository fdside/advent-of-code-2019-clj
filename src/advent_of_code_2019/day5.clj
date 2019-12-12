(ns advent-of-code-2019.day5)

(def input (vec (map
                 read-string
                 (clojure.string/split (slurp "resources/day5input.txt") #","))))


(defn execute [num]
  (loop [memory input
         i 0
         res []]
    (let [op (get memory i)
          a (get memory
                 (if (= 1 (mod (quot op 100) 10))
                   (+ i 1)
                   (get memory (+ i 1))))
          b (get memory
                 (if (= 1 (mod (quot op 1000) 10))
                   (+ i 2)
                   (get memory (+ i 2))))
          target (get memory (+ i 3))]
      (case (mod op 100)
        1 (recur (assoc memory target (+ a b)) (+ i 4) res)
        2 (recur (assoc memory target (* a b)) (+ i 4) res)
        3 (recur (assoc memory (get memory (inc i)) num) (+ i 2) res)
        4 (recur memory (+ i 2) (conj res a))
        5 (recur memory (if (not= 0 a) b (+ i 3)) res)
        6 (recur memory (if (= 0 a) b (+ i 3)) res)
        7 (recur (assoc memory target (if (< a b) 1 0)) (+ i 4) res)
        8 (recur (assoc memory target (if (= a b) 1 0)) (+ i 4) res)
        99 (last res)))))

(defn part1 []
  (execute 1))

(defn part2 []
  (execute 5))

(defn -main [& args]
  (println "part1" (part1))
  (println "part2" (part2)))
