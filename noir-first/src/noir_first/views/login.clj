(ns noir-first.views.login
  (:require [noir-first.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core]
        [hiccup.core]
		[hiccup.page-helpers]
		[hiccup.form-helpers]))

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
(defn valid? [{:keys [username password]}]
	(vali/rule (vali/min-length? username 5)
		[:username "Your usename must be at least 5 characters long"])
	(vali/rule (vali/min-length? password 8)
		[:password "Your password must be at least 8 characters long"])
	(not (vali/errors? :username :password)))

(defpage [:post "/login"] {:keys [username password]}
	(if (valid? login)
		(common/layout
			[:div#content
				[:div#header
					[:h1 "Login"]]
				[:div.announce "You tried to login as " username]])
		(render "/login")))