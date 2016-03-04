package tweedledee

import grails.rest.RestfulController

class AccountController extends RestfulController<Account> {
    
    static responseFormats = ['json', 'xml']
    
    AccountController() {
        super(Account)
    }

   @Override
    protected Account queryForResource(Serializable id) {
        Account.where { id == id }.find()
    }

}