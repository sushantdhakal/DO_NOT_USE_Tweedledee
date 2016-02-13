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

    /**
     * Test 1
     * Requirement: A4
     * Desc: Saving account with unique e-mail but non-unique handle
     */

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

    /**
     * Test 2
     * Requirement: A4
     * Desc: Saving account with unique handle but non-unique email
     */

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

    /**
     * Test 3
     * Requirement: F2
     * Desc: Two accounts may follow each other
     */

    def 'Two accounts may follow each other'() {
        setup:
        def firstAccount = new Account(handle: 'superman', name: 'Clark Kent', email: 'kent@krypton.com', password: 'Banana1234')
        def secondAcount = new Account(handle: 'batman', name: 'Bruce Wayne', email: 'wayne@gotham.net', password: 'Orange1234')
        firstAccount.following = [secondAcount] as Set<Account>
        secondAcount.following = [firstAccount] as Set<Account>

        when:
        firstAccount.save(failOnError: true)
        secondAcount.save(failOnError: true)

        then:
        print(firstAccount.name + "'s followers are: ")
                firstAccount.following.each {follower -> println "${follower.name}"}

        print(secondAcount.name + "'s followers are: ")
        secondAcount.following.each {follower -> println "${follower.name}"}
    }
}
