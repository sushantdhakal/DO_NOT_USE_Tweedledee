package tweedledee

import grails.rest.*

class MessageController extends RestfulController {
    
    static responseFormats = ['json', 'xml']

    MessageController() {
        super(Message)
    }

    @Override
    protected Message queryForResource(Serializable id) {
        def accountID = params.accountId
        Message.where { id == id && account.id == accountID }.find()
    }

    @Override
    protected Message createResource() {

        Message mesg = super.createResource()
        mesg.account = Account.get(params.accountId)
        return mesg;
        
    }

}