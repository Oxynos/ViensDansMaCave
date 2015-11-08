package viensdansmacave

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import spock.lang.*

@TestFor(CellarController)
@Mock(Cellar)
class CellarControllerSpec extends Specification {

    SpringSecurityService springSecurityService = Mock(SpringSecurityService) {getCurrentUser() >> Mock(Member)}
    CellarService cellarService = Mock(CellarService)

    def populateValidParams(params) {
        assert params != null
        params["rate"] = 0.0
        params["member"] = Mock(Member)
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.cellarInstanceList
        model.cellarInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.cellarInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def cellar = new Cellar()
        cellar.validate()
        controller.save(cellar)

        then: "The create view is rendered again with the correct model"
        model.cellarInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        cellar = new Cellar(params)

        controller.save(cellar)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/cellar/show/1'
        controller.flash.message != null
        Cellar.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def cellar = new Cellar(params)
        controller.show(cellar)

        then: "A model is populated containing the domain instance"
        model.cellarInstance == cellar
    }

    void "Test that the showCellar action renders the correct view"() {
        when: "The showCellar is called"
        controller.springSecurityService = springSecurityService
        controller.showCellar()

        then: "The showCellar view is rendered"
        view == '/cellar/showCellar'
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def cellar = new Cellar(params)
        controller.edit(cellar)

        then: "A model is populated containing the domain instance"
        model.cellarInstance == cellar
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/cellar/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def cellar = new Cellar()
        cellar.validate()
        controller.update(cellar)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.cellarInstance == cellar

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        cellar = new Cellar(params).save(flush: true)
        controller.update(cellar)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/cellar/show/$cellar.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/cellar/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def cellar = new Cellar(params).save(flush: true)

        then: "It exists"
        Cellar.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(cellar)

        then: "The instance is deleted"
        Cellar.count() == 0
        response.redirectedUrl == '/cellar/index'
        flash.message != null
    }

    void "Test that the removeWineFromCellar action calls the service method removeWineFromCellar and the correct action is called"() {
        when: "The removeWineFromCellar action is called with a Wine instance"
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService
        Wine wine = Mock(Wine)
        controller.removeWineFromCellar(wine)

        then: "The removeWineFromCellar service method is called"
        1 * cellarService.removeWineFromCellar(_,_)

        and: "The showCellar action is called"
        response.redirectedUrl == '/cellar/showCellar'
    }

    void "Test that the increaseQuantity action calls the service method increaseQuantity"() {
        when: "The increaseQuantity action is called with a WineCellar instance"
        controller.cellarService = cellarService
        WineCellar wineCellar = Mock(WineCellar)
        controller.increaseQuantity(wineCellar)

        then: "The increaseQuantity method of the service is called"
        1 * cellarService.increaseQuantity(_)
    }

    void "Test that the reduceQuantity action calls the service method reduceQuantity"() {
        when: "The reduceQuantity action is called with a WineCellar instance"
        controller.cellarService = cellarService
        WineCellar wineCellar = Mock(WineCellar)
        controller.reduceQuantity(wineCellar)

        then: "The increaseQuantity method of the service is called"
        1 * cellarService.reduceQuantity(_)
    }

    void "test rate cellar access"() {
        given: "a cellar, the spring security service and cellar service"
        def cellar = Mock(Cellar)
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService

        when: "the action is called"
        controller.rateCellar(cellar)

        then: "the rate if it exist is getting from the service"
        1 * cellarService.getRateByUserAndCellar(_, _)

        and: "the correct view is rendering"
        view == '/cellar/rateCellar'
    }

    void "test rate cellar access owner cellar"() {
        given: "a cellar, the spring security service and cellar service"
        def member = Mock(Member)
        def cellar = Mock(Cellar) {getMember() >> member}
        SpringSecurityService springSecurityService = Mock(SpringSecurityService) {getCurrentUser() >> member}
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService

        when: "the action is called"
        controller.rateCellar(cellar)

        then: "the rate if it exist is getting from the service"
        0 * cellarService.getRateByUserAndCellar(_, _)

        and: "the correct view is rendering"
        response.redirectedUrl == '/cellar/index'
        flash.message == "Vous ne pouvez pas noter votre cave !"
    }

    void "test adding rate action"() {
        given: "a cellar, the spring security service and cellar service"
        def cellar = Mock(Cellar)
        def memberRate = Mock(MemberCellarRate) {hasErrors() >> false}
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService
        params["rate"] = 3.0
        params["member"] = Mock(Member)

        when: "the action is called"
        controller.addRate(cellar)

        then: "the rate if it exist is getting from the service"
        1 * cellarService.addRateForCellar(_, _, _) >> memberRate

        and: "the correct view is rendering with good message"
        view == '/cellar/rateCellar'
        flash.message == "Vote enregistrée !"
    }

    void "test adding rate action with errors"() {
        given: "a cellar, the spring security service and cellar service"
        def cellar = Mock(Cellar)
        def memberRate = Mock(MemberCellarRate) {hasErrors() >> true}
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService
        params["rate"] = 3.0
        params["member"] = Mock(Member)

        when: "the action is called"
        controller.addRate(cellar)

        then: "the rate if it exist is getting from the service"
        1 * cellarService.addRateForCellar(_, _, _) >> memberRate

        and: "the correct view is rendering with error message"
        view == '/cellar/rateCellar'
        flash.message == "Votre vote n'a pas été prise en compte !"
    }

    void "test adding rate action with no vote"() {
        given: "a cellar, the spring security service and cellar service and a null rate"
        def cellar = Mock(Cellar)
        def memberRate = Mock(MemberCellarRate) {hasErrors() >> false}
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService
        params["rate"] = null
        params["member"] = Mock(Member)

        when: "the action is called"
        controller.addRate(cellar)

        then: "the rate is not registered"
        0 * cellarService.addRateForCellar(_, _, _) >> memberRate

        and: "the correct view is rendering"
        view == '/cellar/rateCellar'
    }

    void "test adding wine action"() {
        given: "the spring security service and wine service"
        controller.springSecurityService = springSecurityService
        def wineService = Mock(WineService)
        controller.wineService = wineService

        when: "the action is called"
        controller.addWine()

        then: "wines are selected by names and years"
        1 * wineService.findWineNames()
        1 * wineService.findWineYears()

        and: "the correct view is rendering"
        view == '/cellar/addWine'
    }

    void "test finding wine action"() {
        given: "the spring security service and wine service and correct params"
        controller.springSecurityService = springSecurityService
        def wineService = Mock(WineService)
        controller.wineService = wineService
        params["name"] = "test"
        params["year"] = "1981"

        when: "the action is called"
        controller.findWine()

        then: "wines are selected by names and years with results"
        1 * wineService.findWineNames()
        1 * wineService.findWineYears()
        1 * wineService.getWinesByNameAndYear("test", 1981) >> Arrays.asList(Mock(Wine))

        and: "the correct view is rendering"
        view == '/cellar/addWine'

        when: "the action is called"
        controller.findWine()

        then: "wines are selected by names and years without result"
        1 * wineService.findWineNames()
        1 * wineService.findWineYears()
        1 * wineService.getWinesByNameAndYear("test", 1981) >> Collections.emptyList()

        and: "the correct view is rendering and a message appear"
        view == '/cellar/addWine'
        flash.message == 'Aucun vin ne correspond à votre recherche'
    }

    void "test finding wine action with empty name"() {
        given: "the spring security service and wine service and correct params"
        controller.springSecurityService = springSecurityService
        def wineService = Mock(WineService)
        controller.wineService = wineService
        params["name"] = ""
        params["year"] = "1981"

        when: "the action is called"
        controller.findWine()

        then: "wines are selected by names and years"
        1 * wineService.findWineNames()
        1 * wineService.findWineYears()
        1 * wineService.getWinesByNameAndYear(null, 1981) >> Collections.emptyList()

        and: "the correct view is rendering"
        view == '/cellar/addWine'
        flash.message == 'Aucun vin ne correspond à votre recherche'
    }

    void "test adding wine in cellar"() {
        given: "the spring security service and wine service and cellar service"
        def cellar = Mock(Cellar)
        def member = Mock(Member) {getCellar() >> cellar}
        SpringSecurityService springSecurityService = Mock(SpringSecurityService) {getCurrentUser() >> member}
        controller.springSecurityService = springSecurityService
        controller.cellarService = cellarService
        def wineService = Mock(WineService)
        controller.wineService = wineService
        def wine = Mock(Wine)
        params["wine"] = "5"

        when: "the action is called"
        controller.addWineInCellar()

        then: "wines are selected by names and years"
        1 * wineService.find(5.longValue()) >> wine
        1 * cellarService.addWineInCellar(wine, member.cellar) >> Mock(WineCellar) {hasErrors() >> false}

        and: "the correct view is rendering"
        response.redirectedUrl == '/cellar/showCellar'
        flash.message == "Vin ajouté !"

        when: "the action is called"
        controller.addWineInCellar()

        then: "wines are selected by names and years"
        1 * wineService.find(5.longValue()) >> wine
        1 * cellarService.addWineInCellar(wine, member.cellar) >> Mock(WineCellar) {hasErrors() >> true}
        1 * wineService.findWineNames()
        1 * wineService.findWineYears()

        and: "the correct view is rendering"
        view == '/cellar/addWine'
        flash.message == "Erreur lors de l'ajout !"
    }
}
