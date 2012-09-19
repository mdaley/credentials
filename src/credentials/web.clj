(ns credentials.web
  (:use compojure.core
        hiccup.core
        [cheshire.core :only (generate-string parse-string)]
        ring.adapter.jetty
        credentials.utils
        credentials.schemas
        [clojure.data.json :only (read-json json-str)]
        [clojure.data.xml :only [element emit-str]]
        [clojure.string :only (lower-case)])
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

(defn validation-failure [failure]
  {:status 400
  :headers application-json
  :body (json-str {"status" 400 "message" (str "bad request: " (generate-string failure))})})

(defn role-add-success [data]
  {:status 201
   :headers application-json
   :body (generate-string data)})

(defn role-add [body]
  (let [data (parse-string (slurp body) true)
        failure (validateRole data)]
    (if (empty? failure) (role-add-success data) (validation-failure failure))
  )
)

(defn bad-header [header]
  {:status 400
   :headers application-json
   :body (str "bad request: this resource only accepts content type: " header)})

(defn accept-header [header incoming-header function]
    (println incoming-header)
    (if (= header (lower-case (clojure.string/replace incoming-header #" " ""))) function (bad-header header))
  )


(defroutes myroutes
  (GET "/status" {params :query-params} (status (lower-map params)))
  (GET "/ping" [] (ping))
  (POST "/roles" {body :body content-type :content-type} (accept-header "application/json;charset=utf-8" content-type (role-add body)))
  (route/not-found (notfound)))

(def myapp
  (handler/site myroutes))

(defn -main []
  (run-jetty myapp
    {:join? false
     :port 8080}))

