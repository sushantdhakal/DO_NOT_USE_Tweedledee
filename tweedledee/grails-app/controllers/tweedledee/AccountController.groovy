package tweedledee

import grails.rest.*

class AccountController extends RestfulController {
    
    static responseFormats = ['json', 'xml']
    
    def 
    AccountController() {
        super(Account)
    }

    @Override
    protected Account queryForResource(Serializable id) {
        Account.where { id == id }.find()
    }

}