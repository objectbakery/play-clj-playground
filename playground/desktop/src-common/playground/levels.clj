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
  "Collects coordinates for one row and creates the actual [x y] vector"
  [y-pos row]
  (reduce
    (fn [result item]
      (if (= 1 (nth item 1))
        (conj result [(first item) y-pos])
        result))
    []
    (map-indexed vector row)))

(defn- collect-coordinates
  "Collects all coordinates for each row"
  [level-vec]
  (map-indexed
    (fn [row-idx row]
      (collect-rows row-idx row))
    level-vec))

(defn- remove-empty-coordinates
  "Removes empty coordinates vectors"
  [coordinates]
  (filter
    (fn [item]
      (not-empty item))
    coordinates))

(defn- flatten-coordinates
  "Flattens nested coordinates vectors to [[x1 y2] [x2 y2] [x3 y3] ...]"
  [coordinates]
  (reduce
    (fn [result item]
      (concat result item))
    []
    (vec coordinates)))

(defn- transform-to-cam-coordinates
  "Transforms coordinates for correct display"
  [coordinates cam-witdh cam-height]
  (map
    (fn [current-coord]
      (let [x (current-coord 0)
            y (current-coord 1)
            new-y (+ 1 y)]
        [x (- cam-height new-y)]))
    coordinates))

(defn get-cam-coordinates
  "Get vector of level coordinates adjusted for camera"
  [level-vec cam-witdh cam-height]
  (let [flattened-coords (-> level-vec
                             collect-coordinates
                             remove-empty-coordinates
                             flatten-coordinates)]
    (transform-to-cam-coordinates flattened-coords cam-witdh cam-height)))



