(do (clojure.core/ns guestbook.listener (:gen-class :implements [javax.servlet.ServletContextListener])) (do (clojure.core/defn -contextInitialized [this__2446__auto__ G__2527] ((do (clojure.core/require (quote guestbook.handler)) (clojure.core/resolve (quote guestbook.handler/init-app)))) (clojure.core/let [handler__2447__auto__ (clojure.core/let [handler__2438__auto__ (do (clojure.core/require (quote guestbook.handler)) (clojure.core/resolve (quote guestbook.handler/app)))] (clojure.core/fn [request__2439__auto__] (clojure.core/let [context__2440__auto__ (.getContextPath ^{:column 27, :line 127, :tag javax.servlet.http.HttpServletRequest} (:servlet-request request__2439__auto__))] (handler__2438__auto__ (clojure.core/assoc request__2439__auto__ :context context__2440__auto__ :path-info (clojure.core/-> (:uri request__2439__auto__) (clojure.core/subs (.length context__2440__auto__)) clojure.core/not-empty (clojure.core/or "/"))))))) make-service-method__2448__auto__ (do (clojure.core/require (quote ring.util.servlet)) (clojure.core/resolve (quote ring.util.servlet/make-service-method))) method__2449__auto__ (make-service-method__2448__auto__ handler__2447__auto__)] (clojure.core/alter-var-root (do (clojure.core/require (quote guestbook.servlet)) (clojure.core/resolve (quote guestbook.servlet/service-method))) (clojure.core/constantly method__2449__auto__)))) (clojure.core/defn -contextDestroyed [this__2446__auto__ G__2527] nil)))
