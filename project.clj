(defproject cassa-meter-api "0.1.0-SNAPSHOT"
  :description "A simple web app to serve meter data over a JSON API"
  :url "https://github.com/sprin/cassa-meter-api"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring "1.2.0"]
                 [compojure "1.1.5"]
                 [org.clojure/data.json "0.2.2"]
                 [clojurewerkz/cassaforte "1.0.0-rc6-SNAPSHOT"]]
  :plugins [[lein-ring "0.8.6"]]
  :ring {:handler cassa-meter-api.core/app-routes
         :port 8080})
