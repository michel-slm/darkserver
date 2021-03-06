(ns darkserver.views.buildids
  (:require [noir.response :as resp])
  (:use [darkserver.models.buildids]
        [noir.core :only [defpage]]))

(defpage [:get ["/buildids/:id" :id #"\p{XDigit}+[,\p{XDigit}+]*"]]
  {:keys [id]}
  (resp/json
   (map (fn [result]
          {:buildid (result :buildid),
           :rpm (result :rpm),
           :elf (str (result :instpath) "/" (result :elfname)),
           :distro (result :distro)})
        (search-buildid id))))
