(ns playground.core.desktop-launcher
  (:require [playground.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. playground "playground" 800 600)
  (Keyboard/enableRepeatEvents true))
