package tweedledee

import groovy.time.TimeCategory
import grails.rest.RestfulController


class LoginController extends RestfulController<Account> {

	LoginController(){
		super(Account)
	}

	def auth(){

	}

}
  