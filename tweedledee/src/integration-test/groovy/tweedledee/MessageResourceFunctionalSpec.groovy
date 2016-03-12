package tweedledee

import geb.spock.GebSpec
import grails.converters.JSON
import grails.test.mixin.integration.Integration
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Stepwise
import spock.lang.Unroll

/**
 * Account: Functional Test 1
 * Desc: Testing account resource fetch and CRUD operations 
 */

@Unroll
@Integration
@Stepwise
class MessageResourceFunctionalSpec extends GebSpec {

  @Shared
  def accountId

  @Shared
  def accountHandle
  
  @Shared
  def messageId
  
  def validAccountData

  RESTClient restClient

  def setup() {
    
    restClient = new RESTClient(baseUrl)

    validAccountData = [ handle:'Hulk77', name:'Hulk Hogan', email:'thehulkster@hulkomania.me', password:'12345678aA' ]

  }

    def 'set account id'() {

        when:
        def account = new Account( validAccountData )
        def json = account as JSON
        def resp = restClient.post(path: "/account", body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201

        when:
        accountId = resp.data.id
        accountHandle = resp.data.handle

        then:
        accountId
        accountHandle

    }

  /**
   * Test 1
   * Requirement: M2
   * Desc: Returns an error when no messages found for an account
   */
  def 'returns error when no messages found for an account'() {

    when:
    def resp = restClient.get( path: "/account/${accountId}/messages" )

    then:
    HttpResponseException err = thrown(HttpResponseException)
    err.statusCode == 404

  }

  /**
   * Test 2
   * Requirement: M2
   * Desc: Creates a message resource
   */
  def 'Create a new message'() {

    given:
    def mesg = [text:'This is a new message for you']
    def json = mesg as JSON

    when:
    def resp = restClient.post( path: "/account/${accountId}/messages", body: json as String, requestContentType: 'application/json' )

    then:
    resp.status == 201
    resp.data

    when:
    messageId = resp.data.id

    then:
    messageId
    resp.data.text == 'This is a new message for you'
  }

    /**
     * Test 2.1
     * Requirement: M2
     * Desc: Retrieve a saved message
     */
    def 'fetch a saved a message for an account by id'() {

        when:
        def resp = restClient.get( path: "/account/${accountId}/message/${messageId}" )

        then:
        resp.status == 200
        resp.data.id == messageId

    }

    /**
     * Test 2.1.1
     * Requirement: M2
     * Desc: Retrieve a saved message
     */
    def 'fetch a saved a message for an account by hande'() {

        when:
        def resp = restClient.get( path: "/account/${accountHandle}/message/${messageId}" )

        then:
        resp.status == 200
        resp.data.id == messageId

    }

   /* def 'get last ten messages'(){
        given:
        def mesg = [text:'This is a new message for you']
        def json = mesg as JSON

        when:
        def resp = restClient.post( path: "/account/${accountId}/messages", body: json as String, requestContentType: 'application/json' )

        then:
        resp.status == 201
        resp.data

        when:
        def resp1 = restClient.get( path: "/message/${accountId}/messages")

        then:
        resp1.status == 201
    }*/

    /**
     * Test 2.2
     * Requirement: M2
     * Desc: Saved message can be updated
     */
    def 'update a saved a message'() {

        given:
        def mesg = [text:'This is a new message for you ALSO!']
        def json = mesg as JSON

        when:
        def resp = restClient.put( path: "/account/${accountId}/message/${messageId}", body: json as String, requestContentType: 'application/json' )

        then:
        resp.status == 200
        
        when:
        resp = restClient.get(path: "/account/${accountId}/message/${messageId}")

        then:
        resp.status == 200
        resp.data.text == 'This is a new message for you ALSO!'

    }
    /**
     * Test 2.3
     * Requirement: M1
     * Desc: Delete a message resource
     */
    def 'deletes a message'() {

        when:
        def resp = restClient.delete(path: "/account/${accountId}/message/${messageId}")

        then:
        resp.status == 204

        when:
        restClient.get(path: "/account/${accountId}/messages/${messageId}")

        then:
        HttpResponseException err = thrown(HttpResponseException)
        err.statusCode == 404

    }
}