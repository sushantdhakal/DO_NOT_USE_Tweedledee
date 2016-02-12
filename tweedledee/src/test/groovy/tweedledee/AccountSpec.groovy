package tweedledee

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

@TestFor(Account)
@TestMixin(DomainClassUnitTestMixin)
class AccountSpec extends Specification {

    /**
     * Test 1
     * Requirement: A1, successful case
     * Desc: Save account when all required fields are specified
     */

    def 'Account saves when required fields are specified'(){

        given:
        def account1 = new Account(handle:'TheBigEz', name:'Tommy Kramer', email:'thekramster@bigman.net', password:'123456aA')

        when:
        account1.save()

        then:
        account1.id
        !account1.hasErrors()
        println 'TEST 1, the account saved, the name on this account is: ' + account1.name

    }

    /**
     * Test 2
     * Requirement: A1, failure case
     * Desc: Save account when all required fields are not specified (in this case missing the name field)
     */

    def 'Account fails to save when required field is missing'(){

        given:
        def account1 = new Account(handle:'TheBigEz', email:'thekramster@bigman.net',password:'123456aA')

        when:
        account1.save()

        then:
        !account1.id
        account1.hasErrors()
        println 'TEST 2: the account failed to save, the missing field is: ' + account1.errors.getFieldError().field

    }

}
