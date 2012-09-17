(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty
        [clojure.data.json :only (read-json json-str)])
  (:require [compojure.route :as route]))

(defn ping []
  {:status 200
   :headers {"content-type" "text/plain;charset=utf-8"}})

(defn status [params]
  {:status 200}
  :body (pr-str params))

(defn notfound []
  {:status 404
   :headers {"content-type" "application/json;charset=utf-8"}
   :body (json-str {"status" 404 "message" "notfound"})})

(defroutes myroutes
  (GET "/status" {params :params} (status params))
  (GET "/ping" [] (ping))
  (GET "/test" {params :params} (str "<h1>Hello World</h1>" (pr-str params)))
  (route/not-found (notfound)))

(defn -main []
  (run-jetty #'myroutes
    {:join? false
     :port 8080}))

