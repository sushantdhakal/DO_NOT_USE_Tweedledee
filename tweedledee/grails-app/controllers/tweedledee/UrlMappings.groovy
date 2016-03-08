
class UrlMappings {
    
    static mappings = {
        "/"(controller:"error",action:"unauthorized")
        "500"(controller:"error",action:"server")
        "401"(controller:"error",action:"unauthorized")
        "403"(controller:"error",action:"forbidden")
        "404"(controller:"error",action:"notfound")
        "405"(controller:"error",action:"notallowed")
        "/422"(controller:"error",action:"unprocessable")
        "/account"(resources:'account'){
            "/message"(resources:'message')
            "/messages"(resources:'message')
        }
        "/account/$accountId/tweet"(controller:'message',action:'save')
        "/accounts"(controller:'account',action:'list')
        "/accounts/$id"(controller:'account',action:'show')
    } 
}

