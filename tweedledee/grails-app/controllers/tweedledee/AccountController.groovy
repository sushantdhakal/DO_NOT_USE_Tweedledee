package tweedledee

import grails.web.RequestParameter
import grails.rest.RestfulController

class AccountController extends RestfulController<Account> {
    
    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    def testme(@RequestParameter('id') final Long id, @RequestParameter('handle') final String handle) {
//        if(id){
//            respond Account.where { id == id }.find()
//        }else if(handle){
//            respond Account.where { handle == handle }.find()
//        }else{
//            respond status:"id = $id, handle = $handle NOPE"
//        }
    }

    def accountByHandle(){

        def handle=params.id
        if(handle) {
            def acct = Account.where { handle == handle }.find()
            if (acct) {
                respond acct
                return
            } else {
                respond status: "Account was not found for handle $handle"
                return
            }
        } else {
            respond status: "No handle was passed in the request"
            return
        }

    }

    /*@Override
    def show() {
        // We pass which fields to be rendered with the includes attributes,
        // we exclude the class property for all responses.
        respond queryForResource(params), [includes: includeFields, excludes: ['class']]
    }*/

    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Account.list(params), model:[accoutCount: Account.count()]
        //respond listAllResources(params), [includes: includeFields, excludes: ['class']]
    }

    /*@Override
    protected Account listAllResources() {
        def id=params.id
        def handle=params.handle
        if(handle){
            Account.where { handle == handle }.find()
        } else if(id) {
            Account.where { id == id }.find()
        } 
    }*/

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }


}