(ns playground.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [play-clj.math :refer :all]
            [playground.levels :as l]))

(def ^:const cam-width 10)
(def ^:const cam-height 7)

(defn create-shape
  [{:keys [position bounds]}]
  (let [x       (x position)
        y       (y position)
        width   (rectangle! bounds :get-width)
        height  (rectangle! bounds :get-height)]
    (shape :line
           :set-color (color :red)
           :rect x y width height)))

(defn create-block [x-pos y-pos]
  (let [size      (float 1)
        position  (vector-2 x-pos y-pos)
        bounds    (rectangle (x position) (y position) size size)]
    {:size     size
     :position position
     :bounds   bounds}))

(defn create-level [level]
  (let [level-coords (l/get-cam-coordinates level cam-width cam-height)]
    (reduce
      (fn [block-shapes coordinate]
        (let [x (coordinate 0)
              y (coordinate 1)]
          (conj block-shapes (create-shape (create-block x y)))))
      []
      level-coords)))

(defscreen main-screen

   :on-show
   (fn [screen entities]
     (let [camera (orthographic :set-to-ortho false cam-width cam-height)
           screen (update! screen :renderer (stage) :camera camera)]
       (create-level (l/level-one))))

   :on-resize
   (fn [screen entities]
     (height! screen cam-height))

   :on-render
   (fn [screen entities]
     (clear!)
     (render! screen entities)))


(defgame playground
 :on-create
   (fn [this]
     (set-screen! this main-screen)))


;(in-ns 'playground.core)
;(on-gl (set-screen! playground main-screen))
