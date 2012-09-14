(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty))

(defn hello []
  (html [:h1 "Hello!"]))

(defroutes myroutes
  (GET "/" [] (hello)))

(defn -main []
  (run-jetty #'myroutes
    {:join? false
     :port 8080}))

