(ns credentials.schemas
  (:use [closchema.core :as schema]))

(def role
  {:type "object"
   :properties {
                 :id {:type "string" :optional true}
                 :name {:type "string"}
                 :type {:type "string" :default "role"}
                 :status {:type "integer"}
               }
  }
)

(defn validateRole [data] (schema/report-errors (schema/validate role data)))