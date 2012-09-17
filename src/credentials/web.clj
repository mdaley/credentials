(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty
        credentials.utils
        [clojure.data.json :only (read-json json-str)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(defn ping []
  {:status 200
   :headers {"content-type" "text/plain;charset=utf-8"}})

(defn status-json []
  {:status 200
   :body "status-json"
   :headers {"content-type" "application/json;charset=utf-8"}})

(defn status-xml []
  {:status 200
   :body "status-xml"
   :headers {"content-type" "application/xml;charset=utf-8"}})

(defn status [params]
  (if (= "json" (get params "format")) (status-json) (status-xml))
  )

(defn notfound []
  {:status 404
   :headers {"content-type" "application/json;charset=utf-8"}
   :body (json-str {"status" 404 "message" "notfound"})})

(defroutes myroutes
  (GET "/status" {params :query-params} (status (credentials.utils/lower-map params)))
  (GET "/ping" [] (ping))
  (GET "/test" {params :params} (pr-str (params)))
  (route/not-found (notfound)))

(def myapp
  (handler/site myroutes))

(defn -main []
  (run-jetty myapp
    {:join? false
     :port 8080}))

