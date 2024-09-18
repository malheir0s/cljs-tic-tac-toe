(ns tic-tac-toe.home.components
  (:require
   [reagent.core :as r]
   [tic-tac-toe.home.events :as events]))

(defn win-count-container [results-count]
  [:div.container.text-center.mt-2
   [:div.row.mb-4
    [:div.col
     [:h1 "Game Results"]]]
   [:div.row.text-primary-emphasis
    [:div.col.bg-danger-subtle.border.border-primary-sublet.rounded-3
     [:h3.success (str "X Wins: " (get results-count "X"))]]
    [:div.col.bg-success-subtle.border.border-primary-sublet.rounded-3
     [:h3.success (str "O Wins: " (get results-count "O"))]]
    [:div.col.bg-secondary-subtle.border.border-primary-sublet.rounded-3
     [:h3.success (str "Ties: " (get results-count "tie"))]]]])

(defn cell [id state]
  (let [cell-value (get-in @state [:board id])]
    [:div
     {:style {:height "100px"}
      :class (cond-> "col-4 border d-flex justify-content-center align-items-center fs-1"
               (= cell-value "X") (str " bg-danger")
               (= cell-value "O") (str " bg-success"))
      :on-click #(do 
                   (js/console.log (str "Clicked cell: " id))
                   (events/board-click state id))}
     cell-value]))

(defn board-container [state]
  [:div.container.text-center.mt-4
   [:div.row
    [:div.col.mb-4 [:h3 (str "To play: " (:current-player @state))]]]

   (for [row (partition 3 (range 9))]
     ^{:key (str "row-" (first row))}
     [:div.row
      (for [col row]
        ^{:key col}
        [cell col state])])])