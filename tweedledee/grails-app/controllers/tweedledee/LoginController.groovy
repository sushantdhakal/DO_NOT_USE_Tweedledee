package tweedledee

import groovy.time.TimeCategory
import grails.rest.RestfulController


class LoginController extends RestfulController<Account> {

	LoginController(){
		super(Account)
	}

	def auth(){
		//Here check to see if the user exists in the database

		//If yes,

	}

}
  