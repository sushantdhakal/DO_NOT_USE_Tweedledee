import tweedledee.Account
import tweedledee.Role
import tweedledee.AccountRole

class BootStrap {

	def init = { servletContext ->
	    def admin = new Account(handle:'Admin',name:'admin',password:'12345678pP',email:'admin@admin.com').save(flush: true, failOnError: true)
	    def role = new Role(authority: 'ROLE_READ').save(flush: true, failOnError: true)
	    new AccountRole(user: admin, role: role).save(flush: true, failOnError: true)
	}

	def destroy = {
	}

}