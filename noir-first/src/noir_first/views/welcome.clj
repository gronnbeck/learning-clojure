(ns noir-first.views.welcome
  (:require [noir-first.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core]
        [hiccup.core]
		[hiccup.page-helpers]
		[hiccup.form-helpers]))

(defpage "/welcome" []
    (common/layout
		[:p "Welcome to noir-first"]))

(defpage "/" []
	(common/layout
		[:div#content
			[:div#header
				[:h1 "This is my first page"]]
			[:div.announce "This is an announcement, Hello World!"]
			[:ul.content
				[:li "Hope you like it."]]]))
							
(defpartial login-fields [{:keys [username password]}]
	(label "username" "Username: ")
	(text-field "username" username)
	(label "password" "Password: ")
	(password-field "password" password))
			
(defpage "/login" {:as login}
	(common/layout
		[:div#content
			[:div#header
				[:h1 "Login"]]
		(form-to [:post "/login"]
			(login-fields login)
			(submit-button "Login"))]))

(require '[noir.validation :as vali])
(defn valid? [{keys [username password]}]
	(vali/rule (vali/min-length? username 5)
		[:username "Your usename must be at least 5 characters long"])
	(vali/rule (vali/min-length? password 8)
		[:password "Your password must be at least 8 characters long"])
	(not (vali/errors? :username :password)))

(defpage [:post "/login"] {:keys [username password]}
	(common/layout
		[:div#content
			[:div#header
				[:h1 "Login"]]
			[:div.announce "You tried to login as " username]]))
