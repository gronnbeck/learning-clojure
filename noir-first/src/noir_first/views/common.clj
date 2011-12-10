(ns noir-first.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
	(html5
		[:head
			[:title "First Noir Project"]
			(include-css "/css/reset.css")
			(include-css "/css/noir.css")]
		[:body
			[:div#wrapper content]]))