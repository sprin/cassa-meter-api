(ns cassa-meter-api.core
  (:require [clojurewerkz.cassaforte.client :as client]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [clojure.data.json :as json])
  (:use compojure.core
        ring.middleware.stacktrace
        clojurewerkz.cassaforte.cql
        clojurewerkz.cassaforte.query
        ring.util.response))

(defn my-value-writer [key value]
  (if (= key :datetime)
    (str (java.sql.Date. (.getTime value)))
    value))

(defn meter-api []
  (response
    (json/write-str
      {:results (select :meter_samples_day)}
      :value-fn my-value-writer)))


(defroutes app-routes
  (GET "/"
       []
       (meter-api)))

(def app
  (do
    (client/connect! ["127.0.0.1"])
    (use-keyspace "disagg")
    (-> (handler/site app-routes)
        (wrap-stacktrace))))

