package tweedledee

import grails.web.RequestParameter
import grails.rest.RestfulController

class AccountController extends RestfulController<Account> {
    
    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    def accountByHandle(){

        def handle=params.id
        if(handle) {
            def acct = Account.where { handle == handle }.find()
            if (acct) {
                respond acct
                return
            } else {
                respond error:404,message:"Account was not found for handle $handle"
                return
            }
        } else {
            respond error:404,message:"No handle was passed in the request"
            return
        }

    }

    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Account.list(params), model:[accoutCount: Account.count()]
    }


}