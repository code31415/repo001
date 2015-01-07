class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'frontend', action: 'index')
		"/book"(controller: 'frontend', action: 'book')
		"/create"(controller: 'frontend', action: 'create')
		"/about"(controller: 'frontend', action: 'about')
		"500"(view:'/error')
	}
}
