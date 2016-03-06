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
  def messageId
  
  def validAccountData
  def accountResource
  RESTClient restClient

  def setup() {
    
    accountResource = '/accounts'

    restClient = new RESTClient(baseUrl)

    validAccountData = [ handle:'Hulk77', name:'Hulk Hogan', email:'thehulkster@hulkomania.me', password:'12345678aA' ]

  }

    def 'set account id'() {

        when:
        def account = new Account( validAccountData )
        def json = account as JSON
        def resp = restClient.post(path: "${accountResource}", body: json as String, requestContentType: 'application/json')

        then:
        resp.status == 201

        when:
        accountId = resp.data.id

        then:
        accountId

    }

  /**
   * Test 1
   * Requirement: M2
   * Desc: Returns an error when no messages found for an account
   */
  def 'returns error when no messages found for an account'() {

    when:
    def resp = restClient.get( path: "${accountResource}/${accountId}/messages" )

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
    def resp = restClient.post( path: "${accountResource}/${accountId}/messages", body: json as String, requestContentType: 'application/json' )

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
    def 'fetch a saved a message'() {

        when:
        def resp = restClient.get( path: "${accountResource}/${accountId}/messages/${messageId}" )

        then:
        resp.status == 200
        resp.data.id == messageId

    }

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
        def resp = restClient.put( path: "${accountResource}/${accountId}/messages/${messageId}", body: json as String, requestContentType: 'application/json' )

        then:
        resp.status == 200
        
        when:
        resp = restClient.get(path: "${accountResource}/${accountId}/messages/${messageId}")

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
        def resp = restClient.delete(path: "${accountResource}/${accountId}/messages/${messageId}")

        then:
        resp.status == 204

        when:
        restClient.get(path: "${accountResource}/${accountId}/messages/${messageId}")

        then:
        HttpResponseException err = thrown(HttpResponseException)
        err.statusCode == 404

    }
}