
class UrlMappings {
    
    static mappings = {
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }

        "/account"(resources:'account'){
        	"/message"(resources:'message')
        	"/rocker"(resources:'rocker')
        }
        "/"(view:"/index")
        "500"(view:'/error')

    }
}

