(ns tic-tac-toe.home.view
  (:require
   [tic-tac-toe.home.components :as components]
   [tic-tac-toe.home.db :as db]
   [reagent.core :as r]))

(defn page []
  [:<>
   [components/win-count-container (:results-count @db/state)]
   [components/board-container db/state]])
