(ns advent-of-code-2019.day3
  (:require
   [clojure.string :as str]
   [clojure.set :as hset]))


(def input
  (->> (slurp "resources/day3input.txt")
       str/split-lines
       (map #(str/split % #","))))



(defn calc-wire-points [[x y] dir count]
      (case dir
      \R (for [xm (take count (iterate inc (inc x)))] [xm y])
      \L (for [xm (take count (iterate dec (dec x)))] [xm y])
      \U (for [ym (take count (iterate inc (inc y)))] [x ym])
      \D (for [ym (take count (iterate dec (dec y)))] [x ym])))



(defn extend-wire [locations [dir & count]]
  (concat locations
          (calc-wire-points
                     (last locations)
                     dir
                     (read-string (apply str count)))))

(defn create-wire [inp]
  (reduce extend-wire [[0 0]] inp))

(def wire1 (create-wire (first input)))
(def wire2 (create-wire (second input)))
(def intersections (hset/intersection (set wire1) (set wire2)))

(defn abs [n] (max n (- n)))

(defn part1 []
  (->> intersections
       (map #(map abs %))
       (map #(reduce + %))
       sort
       second))

(defn part2 []
  (->> intersections
       (map #(+ (.indexOf wire1 %) (.indexOf wire2 %)))
       sort
       second))

(defn -main [& args]
  (println "part1" (part1))
  (println "part2" (part2)))
