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
}
