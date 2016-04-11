import tweedledee.Account
import tweedledee.Message
import tweedledee.Role
import tweedledee.AccountRole

class BootStrap {

    def init = { servletContext ->
        def admin = new Account(handle:'Admin',name:'admin',password:'12345678pP',email:'admin@admin.com').save(flush: true, failOnError: true)
        def role = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
        new AccountRole(account: admin, role: role).save(flush: true, failOnError: true)

       // def message1 = new Message(text: "Hello1234", dateCreated: new Date(), account: admin).save(flush: true, failOnError: true)
    }

    def destroy = {
    }

}