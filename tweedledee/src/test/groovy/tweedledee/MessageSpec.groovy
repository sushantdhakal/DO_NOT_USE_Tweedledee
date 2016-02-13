package tweedledee

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Message)
@TestMixin(DomainClassUnitTestMixin)
class MessageSpec extends Specification {

    /**
     * Test 1
     * Requirement: M1, successful case
     * Desc: Save message when all required fields are specified
     */

    def 'Message saves when required fields are specified'(){

        given:

        def acct = new Account(handle:'TheBigEz', name:'Tommy Kramer', email:'thekramster@bigman.net', password:'123456aA')
        def mParam = [ text:'abc123',account:acct ]
        def mesg = new Message(mParam)

        when:

        mesg.save()

        then:

        mesg.id
        !mesg.hasErrors()

    }

    /**
     * Test 2
     * Requirement: M1, failure case
     * Desc: Save message when all required fields are not specified (missing text property)
     */

    def 'Message saves fails when required text field is not specified'(){

        given:

        def acct = new Account(handle:'TheBigEz', name:'Tommy Kramer', email:'thekramster@bigman.net', password:'123456aA')
        def mesg = new Message(account:acct)

        when:

        mesg.save()

        then:

        !mesg.id
        mesg.hasErrors()

    }

    /**
     * Test 3
     * Requirement: M1, failure case
     * Desc: Save message when all required fields are not specified (account text property)
     */

    def 'Message saves fails when required account field is not specified'(){

        given:

        def acct = new Account(handle:'TheBigEz', name:'Tommy Kramer', email:'thekramster@bigman.net', password:'123456aA')
        def mesg = new Message(text:'abc123')

        when:

        mesg.save()

        then:

        !mesg.id
        mesg.hasErrors()

    }

    /**
     * Test 4
     * Requirement: M2
     * Desc: Data driven test on save message with both valid and invalid values for 'text' property
     */

    @Unroll
    def 'Message saves when all required fields are entered with valid data'(){

        given:

        def acct = new Account()
        def mParams = [ account:acct, text:mText ]
        def mesg = new Message(mParams)
        mesg.save()

        expect:

        def testHasFailed = !(mesg.hasErrors() == mesgSaveFailed)
        if(testHasFailed==true){
            println ''
            println 'The '+desc.toUpperCase()+' test failed.'+' \nmessage = '+mText
        }
        mesg.hasErrors() == mesgSaveFailed

        where:

        desc                        | mText                                              |   mesgSaveFailed
        "null value"                | null                                               |   true
        "0 characters"              | ""                                                 |   true
        "42 characters"             | "1234567890123456789012345678901234567890 1"       |   true
        "40 characters"             | "1234567890123456789012345678901234567890"         |   false

    }

}
