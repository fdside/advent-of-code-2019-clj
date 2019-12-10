(ns advent-of-code-2019.day4
  (:require
   [clojure.string :as str]))

(def input
  (->> (slurp "resources/day4input.txt")
       (re-seq #"\d+")
       (map read-string)
       (#(range (first %) (inc (second %))))
       (map str)))

(defn is-increasing [num]
  (= num (str/join (sort num))))

(defn digit-frequencies [num]
  (vals (frequencies num)))

(defn has-same-digits? [num]
  (some #(> % 1) (digit-frequencies num)))

(defn contains-double-digit? [num]
  (some #(= 2 %) (digit-frequencies num)))

(defn part1 []
  (->> input
       (filter is-increasing)
       (filter has-same-digits?)
       (count)))


(defn part2 []
  (->> input
       (filter is-increasing)
       (filter contains-double-digit?)
       (count)))

(defn -main
  [& args]
  (println "Part 1:" (part1))
  (println "Part 2:" (part2)))
