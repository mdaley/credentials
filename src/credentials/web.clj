(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty
        [clojure.data.json :only (read-json json-str)])
  (:require [compojure.route :as route]))

(defn hello []
  ;  (html [:h1 "Hello!"])
  (json-str {"Hello" "world"})
  )

(defn ping []
  (html [:h1 "ping"]))

(defn status []
  ("status"))

(defn notfound []
  {:status 404
   :headers {"content-type" "application/json;charset=utf-8"}
   :body (json-str {"status" 404 "message" "notfound"})})

(defroutes myroutes
  (GET "/status" [] {:status 200 :body ("hello")})
  (GET "/ping" [] (ping))
  (route/not-found (notfound)))

(defn -main []
  (run-jetty #'myroutes
    {:join? false
     :port 8080}))

