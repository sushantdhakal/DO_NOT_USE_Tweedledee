
class UrlMappings {
    
    static mappings = {
        /*"/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }*/
        "/"(view:"/index")
        "500"(view:'/error')

        //"/accounts"(controller:'account',action:'index',parseRequest:true)

        "/accounts"(resources:'account'){
            "/messages"(resources:'message')
        }
        //"/account"(controller:"account",action:"testme",parseRequest:true)
        group("/account") {
            "/handle/$id?"(controller:'account',action:'accountByHandle')
        }
    }
}

