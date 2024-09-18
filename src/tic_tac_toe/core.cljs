(ns tic-tac-toe.core
  (:require
   [reagent.dom :as rdom]
   [tic-tac-toe.home.view :as home]))

(defn ^:dev/after-load mount-root []
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [home/page] root-el)))

(defn init []
  (mount-root))
