package tweedledee

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RockerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rocker.list(params), model:[rockerCount: Rocker.count()]
    }

    def show(Rocker rocker) {
        respond rocker
    }

    def create() {
        respond new Rocker(params)
    }

    @Transactional
    def save(Rocker rocker) {
        if (rocker == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rocker.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rocker.errors, view:'create'
            return
        }

        rocker.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rocker.label', default: 'Rocker'), rocker.id])
                redirect rocker
            }
            '*' { respond rocker, [status: CREATED] }
        }
    }

    def edit(Rocker rocker) {
        respond rocker
    }

    @Transactional
    def update(Rocker rocker) {
        if (rocker == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rocker.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rocker.errors, view:'edit'
            return
        }

        rocker.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rocker.label', default: 'Rocker'), rocker.id])
                redirect rocker
            }
            '*'{ respond rocker, [status: OK] }
        }
    }

    @Transactional
    def delete(Rocker rocker) {

        if (rocker == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rocker.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rocker.label', default: 'Rocker'), rocker.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rocker.label', default: 'Rocker'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
