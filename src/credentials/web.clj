(ns credentials.web
  (:use compojure.core
        hiccup.core
        ring.adapter.jetty
        [clojure.data.json :only (read-json json-str)]))

(defn hello []
  ;  (html [:h1 "Hello!"])
  (json-str {"Hello" "world"})
  )

(defn ping []
  (html [:h1 "ping"]))

(defroutes myroutes
  (GET "/" [] (hello))
  (GET "/ping" [] (ping)))

(defn -main []
  (run-jetty #'myroutes
    {:join? false
     :port 8080}))

