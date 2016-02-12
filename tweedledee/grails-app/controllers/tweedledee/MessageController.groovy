package tweedledee

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Message.list(params), model:[messageCount: Message.count()]
    }

    def show(Message message) {
        respond message
    }

    def create() {
        respond new Message(params)
    }

    @Transactional
    def save(Message message) {
        if (message == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (message.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond message.errors, view:'create'
            return
        }

        message.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect message
            }
            '*' { respond message, [status: CREATED] }
        }
    }

    def edit(Message message) {
        respond message
    }

    @Transactional
    def update(Message message) {
        if (message == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (message.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond message.errors, view:'edit'
            return
        }

        message.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect message
            }
            '*'{ respond message, [status: OK] }
        }
    }

    @Transactional
    def delete(Message message) {

        if (message == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        message.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
