package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['permitAll'])
@Transactional(readOnly = true)
class CellarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cellar.list(params), model: [cellarInstanceCount: Cellar.count()]
    }

    def show(Cellar cellarInstance) {
        respond cellarInstance
    }

    def create() {
        respond new Cellar(params)
    }

    @Transactional
    def save(Cellar cellarInstance) {
        if (cellarInstance == null) {
            notFound()
            return
        }

        if (cellarInstance.hasErrors()) {
            respond cellarInstance.errors, view: 'create'
            return
        }

        cellarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cellar.label', default: 'Cellar'), cellarInstance.id])
                redirect cellarInstance
            }
            '*' { respond cellarInstance, [status: CREATED] }
        }
    }

    def edit(Cellar cellarInstance) {
        respond cellarInstance
    }

    @Transactional
    def update(Cellar cellarInstance) {
        if (cellarInstance == null) {
            notFound()
            return
        }

        if (cellarInstance.hasErrors()) {
            respond cellarInstance.errors, view: 'edit'
            return
        }

        cellarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cellar.label', default: 'Cellar'), cellarInstance.id])
                redirect cellarInstance
            }
            '*' { respond cellarInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cellar cellarInstance) {

        if (cellarInstance == null) {
            notFound()
            return
        }

        cellarInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cellar.label', default: 'Cellar'), cellarInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cellar.label', default: 'Cellar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
