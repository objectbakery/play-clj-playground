(ns playground.core.desktop-launcher
  (:require [playground.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(def ^:const scr-width 480)
(def ^:const scr-height 320)

(defn -main
  []
  (LwjglApplication. playground "playground" scr-width scr-height)
  (Keyboard/enableRepeatEvents true))
