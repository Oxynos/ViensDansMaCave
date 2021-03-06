package viensdansmacave


import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('isAuthenticated()')
class CellarController {

    SpringSecurityService springSecurityService
    CellarService cellarService
    WineService wineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def currentMember = springSecurityService.currentUser
        respond Member.list(params), model: [memberInstanceCount: Member.count(), currentMember: currentMember]
    }

    def show(Cellar cellarInstance) {
        respond cellarInstance
    }

    def create() {
        respond new Cellar(params)
    }

    def showCellar(Member member) {
        if (member == null) member = springSecurityService.currentUser
        def currentMember = springSecurityService.currentUser
        def isMe = false
        if (currentMember == member) isMe = true
        render(view: 'showCellar', model: [member: member, isMe: isMe])
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
        if (wines == null) {
            flash.message = 'Sélectionnez au moins un critère !'
        } else if (wines.isEmpty()) {
            flash.message = 'Aucun vin ne correspond à votre recherche'
        }
        render view: "addWine", model: [names: names, years: years, wines: wines]
    }

    def addWineInCellar() {

        def member = springSecurityService.currentUser
        def wine = wineService.find(params.wine as long)
        def quantity = params.quantity as int

        def ret = cellarService.addWineInCellar(wine, member.cellar, quantity)

        if (ret.hasErrors()) {
            def names = wineService.findWineNames()
            def years = wineService.findWineYears()
            render(view: 'addWine', model: [ret: ret, names: names, years: years])
            flash.message = "Erreur lors de l'ajout !"
        } else {
            redirect(action: 'showCellar')
            flash.message = "Vin ajouté !"
        }
    }

    def rateCellar(Cellar cellar) {
        def member = springSecurityService.currentUser
        if (cellar.member.equals(member)) {
            flash.message = "Vous ne pouvez pas noter votre cave !"
            redirect action: "index"
        } else {
            def rate = cellarService.getRateByUserAndCellar(cellar, member)
            render(view: 'rateCellar', model: [rate: rate, cellar: cellar])
        }
    }

    def addRate(Cellar cellar) {
        def member = springSecurityService.currentUser
        if (params.rate) {
            def ret = cellarService.addRateForCellar(cellar, member, params.rate as float)
            if (ret.hasErrors()) {
                flash.message = "Votre vote n'a pas été prise en compte !"
            } else {
                flash.message = "Vote enregistrée !"
            }
        }
        def rate = cellarService.getRateByUserAndCellar(cellar, member)
        render(view: 'rateCellar', model: [rate: rate, cellar: cellar])
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

    def removeWineFromCellar(Wine wine) {
        if (wine == null) {
            notFound()
            return
        }

        Cellar cellar = springSecurityService.currentUser.cellar
        cellarService.removeWineFromCellar(wine, cellar)

        flash.message = "Le vin a bien été supprimé."
        redirect action: "showCellar"
    }

    def reduceQuantity(WineCellar wineCellar) {
        if (wineCellar == null) {
            notFound()
            return
        }

        cellarService.reduceQuantity(wineCellar)
        redirect action: "showCellar"
    }

    def increaseQuantity(WineCellar wineCellar) {
        if (wineCellar == null) {
            notFound()
            return
        }

        cellarService.increaseQuantity(wineCellar)
        redirect action: "showCellar"
    }
}