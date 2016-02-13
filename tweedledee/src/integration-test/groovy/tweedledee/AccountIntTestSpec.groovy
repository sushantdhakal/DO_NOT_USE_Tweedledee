package tweedledee

import grails.test.mixin.TestFor
import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class AccountIntTestSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    def 'Saving account with unique e-mail but non-unique handle'() {
        setup:
        def firstAccount = new Account(handle: 'TheRealSeanJohnson', name: 'Sean Johnson', email: 'johnsanSean@gmail.com', password: 'Orange1234')
        def secondAcount = new Account(handle: 'TheRealSeanJohnson', name: 'Sean Johnson', email: 'seanJohnson@gmail.com', password: 'Orange1234')


        when:
        firstAccount.save(failOnError: true)
        secondAcount.save(failOnError: true)

        then:
        println("Accounts have unique email and handle")
    }

    def 'Saving account with unique handle but non-unique email'() {
        setup:
        def firstAccount = new Account(handle: 'WillSmith', name: 'Sean Johnson', email: 'WillSmith@gmail.com', password: 'Orange1234')
        def secondAcount = new Account(handle: 'TheRealWillSmith', name: 'Sean Johnson', email: 'WillSmith@gmail.com', password: 'Orange1234')


        when:
        firstAccount.save(failOnError: true)
        secondAcount.save(failOnError: true)

        then:
        println("Accounts have unique email and handle")
    }
}
