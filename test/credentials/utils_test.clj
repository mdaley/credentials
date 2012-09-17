(ns credentials.utils-test
  (:use clojure.test
        credentials.utils))

(deftest lower-casing-map-works
  (def map-in {"nO" "MixEd" "CasE" "Map" "herE" "AnymoRe"})
  (def map-out {"no" "mixed" "case" "map" "here" "anymore"})
  (testing "Does this work" (is (= map-out (lower-map map-in)))))