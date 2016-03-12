package tweedledee

import grails.rest.RestfulController

class AccountController extends RestfulController<Account> {
    
    static responseFormats = ['json', 'xml']

    AccountController() {
        super(Account)
    }

    @Override
    def index(final Integer max) {
        params.max = Math.min(max?:10,100)
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
        if(!params.accountId || !params.followerId) _respondError(422,"Invalid account")
        def accountID=_handleAccountId(params.accountId)
        def followerID=_handleAccountId(params.followerId)
        if(accountID==followerID) _respondError(422,"Duplicate account")
        if(followerID){
            def followerAcct=Account.get(followerID)
            if(followerAcct){
                def thisAccount=Account.get(accountID)
                if(thisAccount){
                    followerAcct.followers.add(thisAccount)
                    thisAccount.following.add(followerAcct)
                    _showAccountWithCounts(thisAccount)
                }
            } else _respondError(404,"No account found for follower") 
        } else _respondError(422,"No follower") 
    }

    def showFollowers(final Integer max,final Integer offset){
        
        _fetchFollow(max,offset,"followers")

    }

    def showFollowing(final Integer max,final Integer offset){

         _fetchFollow(max,offset,"following")

    }

    private _fetchFollow(max,offset,galf){
        
        def aa=false
        def limit=Math.min(max?:10,100)
        def os=(offset) ? Math.min(offset,100) : 0
        def accountID=_handleAccountId(params.accountId)
        def acct=Account.get(accountID)
        if(acct) {
            if(galf=='followers') aa=Account.where { id in acct.followers.id }.list([max:limit,offset:os])
            else aa=Account.where { id in acct.following.id }.list([max:limit,offset:os])
            def ff=[]
            aa.each(){ ff.add(name:it.name,handle:it.handle,email:it.email) }
            respond "$galf":ff
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
        
        if(acct) _showAccountWithCounts(acct)
        else _respondError(404,"No account found")

    }

    private _showAccountWithCounts(acct){
        if(acct){
            def resp=[:]
            acct.properties.each{k,v->resp.put(k,v)}
            resp.put('messageCount',acct.messages.size())
            resp.put('followerCount',acct.followers.size())
            resp.put('followingCount',acct.following.size())
            respond resp
        } else _respondError(404,"No account found")
    }
    
    private _respondError(code,mesg){
        response.status=code
        respond error:code,message:"$mesg"
    }
    
}