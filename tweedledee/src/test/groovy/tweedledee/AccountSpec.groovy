package tweedledee

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Account)
@TestMixin(DomainClassUnitTestMixin)
class AccountSpec extends Specification {

    /**
     * Test 1
     * Requirement: A1
     * Desc: Save account when all required fields are specified
     */

    def 'Account saves successfully when all required fields are specified'(){

        given:

        def aParams = [ handle : "TedX1", name : "Teddy T", email : "ttx1@gmail.org", password : '12345678Az' ]
        def acct = new Account(aParams)

        when:

        acct.save()

        then:

        acct.id
        !acct.hasErrors()

    }

    /**
     * Test 2
     * Requirement: A2
     * Desc: Save account fails if any required fields are missing
     */

    @Unroll
    def 'Account save fails when required fields are missing'(){

        given:

        def aParams = [ handle : testHandle, name : testName, email : testEmail, password : testPassword ]
        def acct = new Account(aParams)
        acct.save()

        expect:

        def testHasFailed = !(acct.hasErrors() == accountSaveFail)
        if(testHasFailed==true){
            println ''
            println 'The '+desc.toUpperCase()+' test failed.'+' \nhandle = '+testHandle+' \nname = '+testName+' \nemail = '+testEmail+' \npassword = '+testPassword
        }
        acct.hasErrors() == accountSaveFail

        where:

        desc                  |  testHandle       |  testName |  testEmail    |  testPassword     | accountSaveFail
        "missing handle"      |  ""               |  "Ted"    |  "a@b.com"    |  "12345678aA"     | true
        "missing name"        |  "trox"           |  ""       |  "a@b.com"    |  "12345678aA"     | true
        "missing email"       |  "trox"           |  "Ted"    |  ""           |  "12345678aA"     | true
        "missing password"    |  "trox"           |  "Ted"    |  "a@b.com"    |  ""               | true

    }

    /**
     * Test 3
     * Requirement: A3
     * Desc: Save account fails if an invalid password is entered
     */

    def'Account save fails if invalid password is entered'(){

        given:

        def aParams = [ handle : "TedX9", name : "Ted", email : "ted@bla.com", password : testPassword ]
        def acct = new Account(aParams)
        acct.save()

        expect:

        def testHasFailed = !(acct.hasErrors() == accountSaveFail)
        if(testHasFailed==true){
            println ''
            println 'The '+desc.toUpperCase()+' test failed.'+'\npassword = '+testPassword
        }
        acct.hasErrors() == accountSaveFail

        where:

        desc                      |  testPassword                | accountSaveFail
        "less than 8 chars"       |  "1234567"                   | true
        "over than 16 chars"      |  "123456789012345678"        | true
        "no upper case chars"     |  "12345678a"                 | true
        "no lower case chars"     |  "12345678A"                 | true

    }

}

