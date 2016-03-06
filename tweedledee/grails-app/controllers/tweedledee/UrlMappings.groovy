
class UrlMappings {
    
    static mappings = {
        /*"/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }*/
        "/"(view:"/index")
        "500"(controller:'error',action:'server')
        "401"(controller:'error',action:'unauthorized')
        "403"(controller:'error',action:'forbidden')
        "404"(controller:'error',action:'notfound')
        "405"(controller:'error',action:'notallowed')

        "/accounts"(resources:'account'){
            "/messages"(resources:'message')
        }

        group("/account") {
            "/handle/$id?"(controller:'account',action:'accountByHandle')
        }
    }
}

