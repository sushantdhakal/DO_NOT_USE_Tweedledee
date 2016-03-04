
class UrlMappings {
    
    static mappings = {
        //"/$controller/$action?/$id?"{
           // constraints {
                // apply constraints here
           // }
        //}
        "/"(view:"/index")
        "500"(view:'/error')

        // REST API Mappings
        "/accounts"(resources:'account'){
        	"/messages"(resources:'message')
        }
        

    }
}

