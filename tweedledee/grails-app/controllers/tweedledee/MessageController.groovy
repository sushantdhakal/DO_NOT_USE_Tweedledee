package tweedledee

import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController

class MessageController extends RestfulController<Message> {
    
    static responseFormats = ['json', 'xml']

    MessageController() {
        super(Message)
    }

    def index() {

        def accountID=params.accountId
        def mesg=Message.where { account.id == accountID }.findAll()
        if(mesg){
            respond mesg
            return
        }
        respond null


    }

    @Override
    protected Message queryForResource(Serializable id) {
        def accountID = params.accountId
        Message.where { id == id && account.id == accountID }.find()
    }

    @Override
    protected Message createResource() {
        def t=request.JSON.text
        if(!t||t.size()>40){
            respond error:404,message:"The message must have between 1 and 40 characters to be valid."
            return
        }
        def a=params.accountId
        def b=Account.get(a)
        if(!a||!b){
            respond error:404,message:"The message must be associated to a valid account."
            return
        }
        def p=[text:t,account:[id:a]]
        new Message(p)
    }
    

}