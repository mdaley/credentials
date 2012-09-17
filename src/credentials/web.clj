(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty
        clojure.string
        [clojure.data.json :only (read-json json-str)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defn ping []
  {:status 200
   :headers {"content-type" "text/plain;charset=utf-8"}})

(defn status [params]
  {:status 200
   ;:body (if (= (params :format) "json") "true" "false")
   :body (pr-str params)
   })

(defn notfound []
  {:status 404
   :headers {"content-type" "application/json;charset=utf-8"}
   :body (json-str {"status" 404 "message" "notfound"})})

(defn lower-map [themap]
  (into {} (for [[k v] themap]
             [(lower-case (name k)) (lower-case v)])))

(defroutes myroutes
  (GET "/status" {params :query-params} (status (lower-map params)))
  (GET "/ping" [] (ping))
  (GET "/test" {params :params} (pr-str (lower-case params)))
  (route/not-found (notfound)))

(def myapp
  (handler/site myroutes))

(defn -main []
  (run-jetty myapp
    {:join? false
     :port 8080}))

