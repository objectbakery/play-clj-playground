(ns playground.levels)

(defn level-one []
  [[1 1 1 1 1 1 1 1 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 1 0 0 1]
   [0 0 0 0 0 0 0 0 1]
   [0 0 0 1 1 1 1 1 1]
   [1 1 1 1 1 1 1 1 1]])

(defn level-two []
  [[0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0]
   [1 1 0 0 0 0 0 0 0]])

(defn- collect-rows
  [y row]
  (reduce
    (fn [result item]
      (if (= 1 (nth item 1))
        (conj result [(first item) y])
        result))
    []
    (map-indexed vector row)))

(defn- transform-level-to-coordinates
  [level-vec]
  (map-indexed
    (fn [row-idx row]
      (collect-rows row-idx row))
    level-vec))

(defn- remove-empty-coordinates
  [coordinates]
  (filter
    (fn [item]
      (not-empty item))
    coordinates))

(defn- flatten-coordinates
  [coordinates]
  (reduce
    (fn [result item]
      (concat result item))
    []
    (vec coordinates)))

(defn get-level-coordinates
  [level-vec]
  (let [raw-coordinates       (transform-level-to-coordinates level-vec)
        valid-coordinates     (remove-empty-coordinates raw-coordinates)
        flattened-coordinates (flatten-coordinates valid-coordinates)]
    flattened-coordinates))


