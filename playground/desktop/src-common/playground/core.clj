(ns playground.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [play-clj.math :refer :all]))

(def ^:const scr-width 480)
(def ^:const scr-height 320)
(def ^:const cam-width 10)
(def ^:const cam-height 7)

(defn create-shape
  [{:keys [position bounds]}]
  (let [x       (x position)
        y       (y position)
        width   (rectangle! bounds :get-width)
        height  (rectangle! bounds :get-height)
        rec-x   (rectangle! bounds :get-x)
        rec-y   (rectangle! bounds :get-y)]

    (println (str "x: " x ", y:" y ", height: " height ", width: " width ", rec-x: " rec-x ", rec-y: " rec-y))

    (shape :line
           :set-color (color :red)
           :rect (+ x rec-x) (+ y rec-y) width height)))

(defn create-block [x-pos y-pos]
  (let [size      (float 1)
        position  (vector-2 x-pos y-pos)
        bounds    (rectangle (x position) (y position) size size)]
    {:size     size
     :position position
     :bounds   bounds}))


(defscreen main-screen
   :on-show
   (fn [screen entities]
     (let [camera (orthographic :set-to-ortho false cam-width cam-height)
           block1 (create-shape (create-block 0 0))
           block2 (create-shape (create-block 1 0))
           block3 (create-shape (create-block 2 0))
           block4 (create-shape (create-block 10 7))
           screen (update! screen :renderer (stage) :camera camera)]
       (width! screen scr-width)
       (height! screen scr-height)
       [block1 block2 block3 block4]))

   :on-resize
   (fn [screen entities]
     (height! screen scr-height))

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
