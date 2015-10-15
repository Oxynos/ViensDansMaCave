package viensdansmacave

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('permitAll')
class CellarController {

    SpringSecurityService springSecurityService
    CellarService cellarService
    WineService wineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cellar.list(params), model:[cellarInstanceCount: Cellar.count()]
    }

    def show(Cellar cellarInstance) {
        respond cellarInstance
    }

    def create() {
        respond new Cellar(params)
    }

    @Secured('isAuthenticated()')
    def showCellar() {
        def member = springSecurityService.currentUser
        render(view: 'showCellar', model:[member: member])
    }

    def addWine() {
        def names = wineService.findWineNames()
        def years = wineService.findWineYears()
        render view: "addWine", model: [names: names, years: years]
    }

    def findWine() {
        def names = wineService.findWineNames()
        def years = wineService.findWineYears()
        if (params.name == "")
            params.name = null
        def wines = wineService.getWinesByNameAndYear(params.name, params.year as int)
        if (wines.isEmpty()) {
            flash.message = 'aucun vin ne correspond a votre recherche'
        }
        render view: "addWine", model: [names: names, years: years, wines: wines]
    }

    def addWineInCellar() {
        def member = springSecurityService.currentUser

        def ret = cellarService.addWineInCellar(params.wine, member.cellar)

        if (!ret.hasErrors()) {
            render(view: 'addWine',model:[ret: ret.wine])
        } else {
            render(view: 'addWine',model:[ret: ret])
        }
    }

    @Transactional
    def save(Cellar cellarInstance) {
        if (cellarInstance == null) {
            notFound()
            return
        }

        if (cellarInstance.hasErrors()) {
            respond cellarInstance.errors, view:'create'
            return
        }

        cellarInstance.save flush:true

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
            respond cellarInstance.errors, view:'edit'
            return
        }

        cellarInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cellar.label', default: 'Cellar'), cellarInstance.id])
                redirect cellarInstance
            }
            '*'{ respond cellarInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cellar cellarInstance) {

        if (cellarInstance == null) {
            notFound()
            return
        }

        cellarInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cellar.label', default: 'Cellar'), cellarInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cellar.label', default: 'Cellar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}