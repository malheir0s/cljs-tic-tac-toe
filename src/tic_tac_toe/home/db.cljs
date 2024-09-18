(ns tic-tac-toe.home.db
  (:require
   [reagent.core :as r]))

(def state (r/atom {:board [nil nil nil
                            nil nil nil
                            nil nil nil]
                    :current-player "X"
                    :current-turn 0
                    :results-count {"O" 0
                                    "X" 0
                                    "tie" 0}}))