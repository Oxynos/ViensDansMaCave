package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WineCellarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond WineCellar.list(params), model: [wineCellarInstanceCount: WineCellar.count()]
    }

    def show(WineCellar wineCellarInstance) {
        respond wineCellarInstance
    }

    def create() {
        respond new WineCellar(params)
    }

    @Transactional
    def save(WineCellar wineCellarInstance) {
        if (wineCellarInstance == null) {
            notFound()
            return
        }

        if (wineCellarInstance.hasErrors()) {
            respond wineCellarInstance.errors, view: 'create'
            return
        }

        wineCellarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wineCellar.label', default: 'WineCellar'), wineCellarInstance.id])
                redirect wineCellarInstance
            }
            '*' { respond wineCellarInstance, [status: CREATED] }
        }
    }

    def edit(WineCellar wineCellarInstance) {
        respond wineCellarInstance
    }

    @Transactional
    def update(WineCellar wineCellarInstance) {
        if (wineCellarInstance == null) {
            notFound()
            return
        }

        if (wineCellarInstance.hasErrors()) {
            respond wineCellarInstance.errors, view: 'edit'
            return
        }

        wineCellarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WineCellar.label', default: 'WineCellar'), wineCellarInstance.id])
                redirect wineCellarInstance
            }
            '*' { respond wineCellarInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(WineCellar wineCellarInstance) {

        if (wineCellarInstance == null) {
            notFound()
            return
        }

        wineCellarInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WineCellar.label', default: 'WineCellar'), wineCellarInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wineCellar.label', default: 'WineCellar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
