(ns playground.levels)

(defn level-one []
  [[1 1 1 1 1 1 1 1 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 0 0 0 1]
   [0 0 0 1 1 1 1 1 1]
   [1 1 1 1 1 1 1 1 1]])

(defn- collect-rows
  [row-idx row]
  (reduce
    (fn [result item]
      (if (= 1 (nth item 1))
        (conj result [row-idx (first item)])
        result))
    []
    (map-indexed vector row)))

(defn create-level
  [level-vec]
  (map-indexed
    (fn [row-idx row]
      (collect-rows row-idx row))
    level-vec))


