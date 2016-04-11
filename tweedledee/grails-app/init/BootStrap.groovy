import tweedledee.Account
import tweedledee.Message
import tweedledee.Role
import tweedledee.AccountRole

class BootStrap {

    def init = { servletContext ->
        def admin = new Account(handle:'Admin',name:'admin',password:'12345678pP',email:'admin@admin.com').save(flush: true, failOnError: true)
        def role = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
        new AccountRole(account: admin, role: role).save(flush: true, failOnError: true)

        def admin1 = new Account(handle:'sushantdhakal',name:'sushantdhakal',password:'12345678pP',email:'sushant@admin.com').save(flush: true, failOnError: true)
        def role1 = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
        new AccountRole(account: admin1, role: role1).save(flush: true, failOnError: true)

        def admin2 = new Account(handle:'paulM',name:'paulM',password:'12345678pP',email:'paul@admin.com').save(flush: true, failOnError: true)
        def role2 = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
        new AccountRole(account: admin2, role: role2).save(flush: true, failOnError: true)

        (1..20).each {
            msgNumber -> admin.addToMessages(text: "This my admin's message# $msgNumber").save(flush: true, failOnError: true)
        }

        (1..10).each {
            msgNumber -> admin1.addToMessages(text: "This Sushant's message# $msgNumber").save(flush: true, failOnError: true)
        }

        (1..10).each {
            msgNumber -> admin2.addToMessages(text: "This my Paul message# $msgNumber").save(flush: true, failOnError: true)
        }
    }

    def destroy = {
    }

}