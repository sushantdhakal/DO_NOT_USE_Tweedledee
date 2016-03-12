package tweedledee

import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController

class MessageController extends RestfulController<Message> {
    
    static responseFormats = ['json', 'xml']

    MessageController() {
        super(Message)
    }

    @Override
    def index(final Integer max) {
        if(params.accountId){
            params.max = Math.min(max?:10,100)
            def accountID = _handleAccountId(params.accountId)
            def mesg=Message.where { account.id == accountID }.list(params)
            if(mesg) respond mesg
            else _respondError(404,"No messages found")
        }
        _respondError(422,"No account")
    }

    @Override
    def show(){
        def id=params.id
        def accountID = _handleAccountId(params.accountId)
        def mesg=Message.where { id == id && account.id == accountID }.find()
        respond _formatMessage(mesg)
    }

    def lastTenMessages(final Integer max,final Integer offset){
        def limit = Math.min(max?:10,100)
        def os = (offset) ? Math.min(offset,100) : 0
        def accountID= _handleAccountId(params.accountId)
        def account = Account.get(accountID)
        if(account){
            def mesg = Message.where { account.id == accountID }.list(max:limit, offset:os)
            if(mesg) {
                def resp=[]
                mesg.each(){
                    resp.add( _formatMessage(it) )
                }
                respond resp
            } else {
                 _respondError(404,"No messages found")
            }
        } else _respondError(404,"No account found")
    }

    def searchMessages(final Integer max){
        def searchText=request.JSON.searchText
        def limit = Math.min(max?:10,100)
        if(searchText){
            def m = Message.createCriteria()
            def res = m.list(max:limit) {
                like("text", "%${searchText}%")
            }
            if(res) respond res
            else _respondError(404,"No messages found for search '$searchText'")
        } else _respondError(422,"No search criteria")
    }

    @Override
    def create(){
        _respondError(404,"Not found")
    }

    @Override
    protected Message queryForResource(Serializable id) {
        def accountID = _handleAccountId(params.accountId)
        Message.where { id == id && account.id == accountID }.find()
    }

    @Override
    protected Message createResource() {
        def text=request.JSON.text
        if(!text||text.size()>40){
            _respondError(422,"The message must have between 1 and 40 characters to be valid.")
            return
        }
        def accountID = _handleAccountId(params.accountId)
        def account=Account.get(accountID)
        if(!accountID||!account){
            _respondError(422,"The message must be associated to a valid account.")
            return
        }
        def p=[text:text,account:[id:accountID]]
        new Message(p)
    }

    private _formatMessage(mesg){
        if(mesg){
            def resp=[:]
            def cd=_formatDate(mesg.dateCreated)
            resp.put('id',mesg.id)
            resp.put('text',mesg.text)
            resp.put('dateCreated',cd)
            return resp
        } else _respondError(404,"No account found")
    }

    private _handleAccountId(accountID){
        def isNum = (accountID as String).isNumber()
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

    private _formatDate(undate){
        def ep=Calendar.getInstance(TimeZone.getTimeZone('CST'))
        def dt=undate as Long
        ep.setTimeInMillis(dt)
        return ep.format("MM/dd/yyyy HH:mm:ss zzz")
    }

    private _respondError(code,mesg){
        response.status=code
        respond error:code,message:"$mesg"
    }
}