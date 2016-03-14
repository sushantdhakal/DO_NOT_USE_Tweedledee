package tweedledee

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Unroll

@Unroll
@Integration
@Stepwise
class FollowersFunctionalSpec extends GebSpec {

    @Shared
    def accountId

    @Shared
    def accountHandle

    @Shared
    def followerId

    @Shared
    def followerCount

    @Shared
    def followingCount

    RESTClient restClient

    def setup() {

        restClient = new RESTClient(baseUrl)
        def rr = restClient.get( path: "/init" )
        if(rr.status==200) {
            accountId=2
            accountHandle="hulk_2"
        }

    }


    // Req: F1
    def 'one account can follow another'(){

        when:
        def acct=new Account([ handle:'lone1', name:'NotFollowed', email:'thehulkster@hulkomania.me', password:'12345678aA' ])
        def json=acct as JSON
        def resp = restClient.post( path: "/account", body: json as String, requestContentType: 'application/json' )

        then:
        resp.status == 201
        resp.data.id

        when:
        followerId = resp.data.id
        resp = restClient.get( path: "/account/2" )

        then:
        resp.status == 200
        resp.data.followingCount

        when:
        def isFollowing = false
        followingCount = resp.data.followingCount
        resp = restClient.get( path: "/account/2/follow",query:[followerId:followerId] )
        if(resp.status==200){
            resp.data.following.each(){ if(it.id==followerId) isFollowing=true }
        }

        then:
        resp.status == 200
        resp.data.followingCount == followingCount+1
        isFollowing
    }

    // Req: F2
    def 'account has followerCount and followingCount property'() {

        when:
        def resp = restClient.get(path: "/account/1")

        then:
        resp.status == 200
        resp.data.followerCount
        resp.data.followingCount

        when:
        followerCount = resp.data.followerCount
        followingCount = resp.data.followingCount
        resp = restClient.get( path: "/account/1/followers" )

        then:
        resp.status == 200
        resp.data.followers.size() == followerCount

        when:
        resp = restClient.get( path: "/account/2/following" )

        then:
        resp.status == 200
        resp.data.following.size() == followingCount

    }

    // Req: F3
    def 'get followers for an account with optional limit and offset'() {

        when:
        def resp = restClient.get( path: "/account/2/followers" )

        def hasFollowerId=false
        def hasFollowerHandle=false
        def hasFollowerName=false
        def hasFollowerEmail=false
        if(resp.status==200){
            resp.data.followers.each(){
                k,v->
                    if(k=='id') hasFollowerId=true
                    if(k=='handle') hasFollowerHandle=true
                    if(k=='name') hasFollowerName=true
                    if(k=='email') hasFollowerEmail=true
            }
        }

        then:
        resp.status == 200
        resp.data.followers.size() != 0
        hasFollowerId
        hasFollowerHandle
        hasFollowerName
        hasFollowerEmail

        when:
        followerCount = resp.data.followers.size()
        def limit = followerCount-1
        resp = restClient.get( path: "/account/2/followers",query:[max:limit] )

        then:
        resp.status == 200
        resp.data.followers.size() == limit

        when:
        followerId = resp.data.followers[0].id
        resp = restClient.get( path: "/account/2/followers",query:[offset:2] )

        then:
        resp.status == 200
        resp.data.followers[0].id != followerId

    }

    // Req: F4
    def 'account feed that returns most recent messages with opional limit and show data after param'() {

        when:
        def resp = restClient.get( path: "/account/2/feed" )

        then:
        resp.status == 200
        resp.data.messages
        resp.data.messages.size() == resp.data.count

        when:
        resp = restClient.get( path: "/account/2/feed",query:[max:3] )

        then:
        resp.status == 200
        resp.data.messages
        resp.data.messages.size() == 3

        when:
        def dateBeforeParam = false
        def dateParam = Date.parse('MM/dd/yyyy HH:mm:ss','03/12/2016 00:00:00')
        resp = restClient.get( path: "/account/2/feed",query:[date:"03/12/2016"] )
        if(resp.status==200){
            resp.data.messages.each(){
                if(it.dateCreated<dateParam) dateBeforeParam = true
            }
        }

        then:
        resp.status == 200
        resp.data.messages
        resp.data.messages.size() == resp.data.count
        !dateBeforeParam

    }

}