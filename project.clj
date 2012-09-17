(defproject credentials "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/data.json "0.1.3"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.1"]
                 [ring "1.1.5"]]
  :main credentials.web
; lein ring allows server to run with 'lein ring server' & any code
; changes will be picked up immediately by the running server.
  :profiles {:dev {:plugins [[lein-ring "0.7.4"]]
                   :ring {:handler credentials.web/myapp}}})
