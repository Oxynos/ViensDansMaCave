package viensdansmacave


import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import wine.WineColor

@TestFor(WineController)
@Mock(Wine)
class WineControllerSpec extends Specification {

    WineService wineService = Mock(WineService) {save(_) >> Mock(Wine)}
    WineService wineServiceErr = Mock(WineService) {save(_) >> Mock(Wine) {hasErrors() >> true}}

    def populateValidParams(params) {
        assert params != null
        params["name"] = "un nom"
        params["year"] = 1990
        params["color"] = WineColor.RED
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.wineInstanceList
        model.wineInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.wineInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def wine = new Wine()
        wine.validate()
        controller.save(wine)

        then: "The create view is rendered again with the correct model"
        model.wineInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        wine = new Wine(params)

        controller.save(wine)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/wine/show/1'
        controller.flash.message != null
        Wine.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def wine = new Wine(params)
        controller.show(wine)

        then: "A model is populated containing the domain instance"
        model.wineInstance == wine
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def wine = new Wine(params)
        controller.edit(wine)

        then: "A model is populated containing the domain instance"
        model.wineInstance == wine
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/wine/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def wine = new Wine()
        wine.validate()
        controller.update(wine)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.wineInstance == wine

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        wine = new Wine(params).save(flush: true)
        controller.update(wine)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/wine/show/$wine.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/wine/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def wine = new Wine(params).save(flush: true)

        then: "It exists"
        Wine.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(wine)

        then: "The instance is deleted"
        Wine.count() == 0
        response.redirectedUrl == '/wine/index'
        flash.message != null
    }

    void "Test that the saveAndAddInCellar action redirects to the correct action"() {
        when: "The saveAndAddInCellar action is called with a correct Wine instance"
        controller.wineService = wineService
        Wine wine = Mock(Wine)
        controller.saveAndAddInCellar(wine)

        then: "The addWineInCellar action of the Cellar controller is called"
        response.redirectedUrl == '/cellar/addWineInCellar?wine=&quantity=1'

        when: "The saveAndAddInCellar action is called with an incorrect Wine instance"
        controller.wineService = wineServiceErr
        controller.saveAndAddInCellar(Mock(Wine))

        then: "The create view is rendered again"
        view == "/wine/create"
    }
}
