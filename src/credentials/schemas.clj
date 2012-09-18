(ns credentials.schemas
  (:use [closchema.core :as schema]))

(def role
  {:type "object"
   :properties {
                 ; Descriptive id for the role
                 :id {:type "string"}
                 ; type of the object, use when the object is returned in service responses.
                 :type {:type "string" :default "role" :optional true}
                 ; status of the response, e.g. 201 when a role has just been created.
                 :status {:type "integer" :optional true}
               }
  }
)

(defn validateRole [data] (schema/report-errors (schema/validate role data)))