package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HomeController)
class HomeControllerSpec extends Specification {

    CellarService cellarService = Mock(CellarService)

    def setup() {
        controller.cellarService = cellarService
    }

    def cleanup() {
    }

    void "Test that the index action calls the service method"() {
        when: "The index action is called"
        controller.index()

        then: "The service method wineRanking is called"
        1 * cellarService.wineRanking()
        1 * cellarService.getCellarRanking()
    }
}
