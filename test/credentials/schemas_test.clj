(ns credentials.schemas-test
  (:use clojure.test
        credentials.schemas
        [cheshire.core :as cheshire]))

(deftest a-test
  (testing "Does this work" (is (= 1 1))))

(deftest test-role-validation-success
  (def data {:id "1234" :name "rolename" :status 200 :type "role"})
  (testing "role validation against schema success" (is (= true (empty? (validateRole data)))))
  )

(deftest test-role-validation-failure
  (def data {:id "1234" :status 200 :type "role"})
  (testing "role validation against schema failure for missing mandatory field" (is (= false (empty? (validateRole data)))))
  )

(deftest test-role-validation-success-optional-field-not-present
  (def data {:name "rolename" :status 200 :type "role"})
  (println (cheshire/generate-string (validateRole data)))
  (testing "role validation against schema failure for missing mandatory field" (is (= true (empty? (validateRole data)))))
  )