(ns tic-tac-toe.home.events
  (:require [clojure.set :as set]))

(def winning-positions [#{0 1 2}
                        #{3 4 5}
                        #{6 7 8}
                        #{0 3 6}
                        #{1 4 7}
                        #{2 5 8}
                        #{0 4 8}
                        #{2 4 6}])

(defn check-winner
  "Checks if the current play leads to a win. Min turns to have a winner is 5.
   Gets all the current positions of the player that just made a move, see if there is any combination of winning-positions
   by using a set diff. If the difference is empty, means that the current player has all necessary positions to win."
  [state position current-player]
  (when (> (:current-turn @state) 3)
    (let [current-player-positions (into #{position} (map-indexed (fn [idx value]
                                                                    (when (= current-player value)
                                                                      idx))
                                                                  (:board @state)))

          winner? (->> winning-positions
                       (map #(set/difference % current-player-positions))
                       (filter empty?))]

      (when (seq winner?)
        current-player))))

(defn board-click
  "Handles the click on the board. Clicking on a square that has not been clicked will change the state"
  [state position]
  (let [current-player (:current-player @state)
        winner (check-winner state position current-player)
        last-move? (= (:current-turn @state) 8)]

    (when (nil? (get-in @state [:board position]))
      (swap! state assoc-in [:board position] current-player)
      (swap! state assoc :current-player (if (= current-player "X")
                                           "O"
                                           "X"))
      (swap! state update :current-turn inc)

      (when (or (seq winner)
                last-move?)
        (js/setTimeout (fn []
                         (js/alert (if winner
                                    (str winner " Wins !")
                                    "It's a tie!"))
                         (swap! state update-in [:results-count (if winner
                                                                  winner
                                                                  "tie")] inc)
                         (swap! state assoc :current-turn 0 :board [nil nil nil
                                                                    nil nil nil
                                                                    nil nil nil])) 
                       1)))))