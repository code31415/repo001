class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'frontend', action: 'index')
		"/book"(controller: 'frontend', action: 'book')
		"500"(view:'/error')
	}
}
