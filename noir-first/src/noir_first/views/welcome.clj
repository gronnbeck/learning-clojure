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
							

