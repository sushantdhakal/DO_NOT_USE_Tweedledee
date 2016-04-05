
class UrlMappings {
    
    static mappings = {
       //"/"(view:"index")
        "/account"(resources:'account'){
            "/message"(resources:'message')
        }
        "/account/$accountId/follow"(controller:'account',action:'addFollower')
        "/account/$accountId/followers"(controller:'account',action:'showFollowers')
        "/account/$accountId/following"(controller:'account',action:'showFollowing')
        "/message/${accountId}/messages"(controller:'message',action:'lastTenMessages')
        "/messages/search"(controller:'message',action:'searchMessages')
        "/account/$accountId/feed"(controller:'account',action:'showFeed')
        "/init"(controller:'account',action:'initMe')
        "/"(view:'/index')
        "/login"(controller:'account',action:'auth')
        "/punk"(controller:'account',action:'initAdmin')
    }
}

