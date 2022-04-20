(ns guestbook.handler
  (:require
    [guestbook.middleware :as middleware]
    [guestbook.layout :refer [error-page]]
    [guestbook.routes.home :refer [home-routes]]
    [reitit.ring :as ring]
    [ring.middleware.content-type :refer [wrap-content-type]]
    [ring.middleware.webjars :refer [wrap-webjars]]
    [guestbook.env :refer [defaults]]
    [mount.core :as mount]))

(mount/defstate init-app
  :start ((or (:init defaults) (fn [])))
  :stop  ((or (:stop defaults) (fn []))))


;; (defn init
;;   "init will be called once when
;;    app is deployed as a servlet on
;;    an app server such as Tomcat
;;    put any initialization code here"
;;   []
;;   (doseq [component (:started (mount/start))]
;;     (log/info component "started")))

;; (defn destroy
;;   "destroy will be called when your application
;;    shuts down, put any clean up code here"
;;   []
;;   (doseq [component (:stopped (mount/stop))]
;;     (log/info component "stopped"))
;;   (shutdown-agents)
;;   (log/info "guestbook has shut down!"))


(mount/defstate app-routes
  :start
  (ring/ring-handler
    (ring/router
      [(home-routes)])
    (ring/routes
      (ring/create-resource-handler
        {:path "/"})
      (wrap-content-type
        (wrap-webjars (constantly nil)))
      (ring/create-default-handler
        {:not-found
         (constantly (error-page {:status 404, :title "404 - Page not found"}))
         :method-not-allowed
         (constantly (error-page {:status 405, :title "405 - Not allowed"}))
         :not-acceptable
         (constantly (error-page {:status 406, :title "406 - Not acceptable"}))}))))

(defn app []
  (middleware/wrap-base #'app-routes))
