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
        else {
            def accts=Account.list(params)
            if(accts) respond accts, model:[accoutCount: Account.count()]
            else _respondError(404,"No accounts found")
        }
    }

    @Override
    def show(){
        if(params.id) _handleParams(params)
        else _respondError(404,"No account found")    
    }

    def addFollower(){
        if(!params.accountId || !params.follower) _respondError(422,"Invalid account")
        def accountID=_handleAccountId(params.accountId)
        def followerID=_handleAccountId(params.follower)
        if(followerID){
            def followerAcct=Account.get(followerID)
            if(followerAcct){
                def thisAccount=Account.get(accountID)
                if(thisAccount){
                    thisAccount.followers.add(followerAcct)
                    followerAcct.following.add(thisAccount)
                    respond thisAccount
                }
            } else _respondError(404,"No account found for follower") 
        } else _respondError(422,"No follower") 
    }

    def showFollowers(){
        def accountID=_handleAccountId(params.accountId)
        def acct=Account.get(accountID)
        if(acct) {
            def followers = []
            acct.followers.each(){
                followers.add(id:it.id,name:it.name,handle:it.handle,email:it.email)
            }
            respond followers:followers
        } else _respondError(404,"No account found") 
    }

    def showFollowing(){
        def max = (params.max) ? params.max : 10
        def offset = (params.offset) ? params.offset : 5
        def accountID=_handleAccountId(params.accountId)
        //def acct=Account.executeQuery("select a.following from Account a where a.id = ?",[accountID],[max:max,offset:offset])
        def acct=Account.get(accountID)
        if(acct) {
            def following = []
            acct.following.each(){
                following.add(id:it.id,name:it.name,handle:it.handle,email:it.email)
            }
            respond following:following
        } else _respondError(404,"No account found") 
    }

    private _handleAccountId(accountID){
        def id=accountID
        def isNum = (id as String).isNumber()
        if(!isNum){
            def acct=Account.findByHandle(accountID)
            if(acct) return acct.id
            else _respondError(422,"Invalid account")
        }else if(accountID){
            return accountID
        }else {
            _respondError(422,"No account")
        }
    }

    private _handleParams(Map params){
        def isNum = (params.id) ? (params.id as String).isNumber() : false
        def acct=null
        if(params.id && isNum) acct=Account.get(params.id)
        else if(params.id && !isNum) acct=Account.findByHandle(params.id)
        else if(params.handle) acct=Account.findByHandle(params.handle)
        
        if(acct) respond acct, model:[followerCount:acct.followers.count(),followingCount:acct.following.count(),messageCount:acct.messages.count()]
        else _respondError(404,"No account found")

    }

    private _respondError(code,mesg){
        response.status=code
        respond error:code,message:"$mesg"
    }
    
}