package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WineController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Wine.list(params), model: [wineInstanceCount: Wine.count()]
    }

    def show(Wine wineInstance) {
        respond wineInstance
    }

    def create() {
        respond new Wine(params)
    }

    @Transactional
    def save(Wine wineInstance) {
        if (wineInstance == null) {
            notFound()
            return
        }

        if (wineInstance.hasErrors()) {
            respond wineInstance.errors, view: 'create'
            return
        }

        wineInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wine.label', default: 'Wine'), wineInstance.id])
                redirect wineInstance
            }
            '*' { respond wineInstance, [status: CREATED] }
        }
    }

    def edit(Wine wineInstance) {
        respond wineInstance
    }

    @Transactional
    def update(Wine wineInstance) {
        if (wineInstance == null) {
            notFound()
            return
        }

        if (wineInstance.hasErrors()) {
            respond wineInstance.errors, view: 'edit'
            return
        }

        wineInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Wine.label', default: 'Wine'), wineInstance.id])
                redirect wineInstance
            }
            '*' { respond wineInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Wine wineInstance) {

        if (wineInstance == null) {
            notFound()
            return
        }

        wineInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Wine.label', default: 'Wine'), wineInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wine.label', default: 'Wine'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
