(ns credentials.schemas-test
  (:use clojure.test
        credentials.schemas
        [cheshire.core :as cheshire]))

(deftest test-role-validation-success
  (def data {:id "administrator" :status 200 :type "role"})
  (testing "role validation against schema success" (is (= true (empty? (validateRole data)))))
  )

(deftest test-role-validation-failure
  (def data {:status 201 :type "role"})
  (testing "role validation against schema failure for missing mandatory field" (is (= false (empty? (validateRole data)))))
  )

(deftest test-role-validation-success-optional-field-not-present
  (def data {:id "care_administrator"})
  (testing "role validation against schema failure for missing mandatory field" (is (= true (empty? (validateRole data)))))
  )