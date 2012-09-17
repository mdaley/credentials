(ns credentials.web
  (:use compojure.core
        hiccup.core
        cheshire.core
        ring.adapter.jetty
        credentials.utils
        [clojure.data.json :only (read-json json-str)]
        [clojure.data.xml :only [element emit-str]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(def service-name "credentials")
(def application-json {"content-type" "application/json;charset=utf-8"})
(def application-xml {"content-type" "application/xml;charset=utf-8"})
(def text-plain {"content-type" "text/plain;charset=utf-8"})

(defn service-version []
  "0.0.1")

(defn service-status []
  (str true))

(defn ping []
  {:status 200
   :headers text-plain})

(defn status-json []
  {:status 200
   :body (json-str {:serviceName service-name :version (service-version) :status (service-status)})
   :headers application-json})

(defn status-xml []
  {:status 200
   :body (emit-str (element :status {:serviceName service-name :version (service-version) :success (service-status)}))
   :headers application-xml})

(defn status [params]
  (if (= "json" (get params "format")) (status-json) (status-xml))
  )

(defn notfound []
  {:status 404
   :headers application-json
   :body (json-str {"status" 404 "message" "notfound"})})

(defn role-add [body]
  {:status 200
   :headers application-json
   :body (slurp body)})

(defroutes myroutes
  (GET "/status" {params :query-params} (status (lower-map params)))
  (GET "/ping" [] (ping))
  (POST "/roles" {body :body} (role-add body))
  (route/not-found (notfound)))

(def myapp
  (handler/site myroutes))

(defn -main []
  (run-jetty myapp
    {:join? false
     :port 8080}))

