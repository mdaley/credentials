(ns credentials.utils
  (:use clojure.string))

(defn lower-map [themap]
  (into {} (for [[k v] themap]
             [(lower-case (name k)) (lower-case v)])))