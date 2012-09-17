(ns credentials.utils
  (:use [clojure.string :only (lower-case)]))

(defn lower-map [themap]
  (into {} (for [[k v] themap]
             [(lower-case (name k)) (lower-case v)])))

