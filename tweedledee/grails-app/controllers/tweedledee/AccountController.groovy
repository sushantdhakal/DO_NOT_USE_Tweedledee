package tweedledee

import grails.rest.RestfulController

class AccountController extends RestfulController<Account> {
    
    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if(params.id || params.handle) _handleParams(params)
        else respond Account.list(params), model:[accoutCount: Account.count()]
    }

    @Override
    def show(){
        if(params.id) _handleParams(params)
        else _respondError(404,"No account found")    
    }

    private _handleParams(Map params){
        def isNum = (params.id) ? (params.id as String).isNumber() : false
        if(params.id && isNum) respond Account.get(params.id)
        else if(params.id && !isNum) respond Account.findByHandle(params.id)
        else if(params.handle) respond Account.findByHandle(params.handle)
        else _respondError(404,"No accounts found")
    }

    private _respondError(code,mesg){
        response.status=code
        respond error:code,message:"$mesg"
    }
    
}