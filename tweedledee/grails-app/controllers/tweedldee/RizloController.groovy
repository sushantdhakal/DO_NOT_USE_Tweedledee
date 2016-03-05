package tweedldee

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RizloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rizlo.list(params), model:[rizloCount: Rizlo.count()]
    }

    def show(Rizlo rizlo) {
        respond rizlo
    }

    def create() {
        respond new Rizlo(params)
    }

    @Transactional
    def save(Rizlo rizlo) {
        if (rizlo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rizlo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rizlo.errors, view:'create'
            return
        }

        rizlo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rizlo.label', default: 'Rizlo'), rizlo.id])
                redirect rizlo
            }
            '*' { respond rizlo, [status: CREATED] }
        }
    }

    def edit(Rizlo rizlo) {
        respond rizlo
    }

    @Transactional
    def update(Rizlo rizlo) {
        if (rizlo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rizlo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rizlo.errors, view:'edit'
            return
        }

        rizlo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rizlo.label', default: 'Rizlo'), rizlo.id])
                redirect rizlo
            }
            '*'{ respond rizlo, [status: OK] }
        }
    }

    @Transactional
    def delete(Rizlo rizlo) {

        if (rizlo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rizlo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rizlo.label', default: 'Rizlo'), rizlo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rizlo.label', default: 'Rizlo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
