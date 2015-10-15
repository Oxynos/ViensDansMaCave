package viensdansmacave

import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CellarService)
class CellarServiceSpec extends Specification {

    CellarDAOService cellarDAOService

    def setup() {
        cellarDAOService = Mock(CellarDAOService) {wineIsInCellar(_,_) >> Mock(WineCellar)}
        service.cellarDAOService = cellarDAOService
    }

    def cleanup() {
    }

    void "Test the removeWineFromCellar method calls the CellarDAOService method"() {
        given : "A wine instance and a cellar instance"
        Wine wine = Mock(Wine)
        Cellar cellar = Mock(Cellar)

        when : "The removeWineFromCellar method is called"
        service.removeWineFromCellar(wine, cellar)

        then : "The removeWineFromCellar method of the CellarDAOService is called"
        1 * cellarDAOService.removeWineFromCellar(_)
    }

    void "Test the removeWineFromCellar method return false when the wine isn't in the cellar"() {
        given: "A wine instance which isn't in the cellar instance"
        Wine wine = Mock(Wine)
        Cellar cellar = Mock(Cellar)

        when: "The removeWineFromCellar method is called"
        service.cellarDAOService = Mock(CellarDAOService) {wineIsInCellar(_,_) >> null}
        def ret = service.removeWineFromCellar(wine, cellar)

        then: "The removeWineFromCellar method return false"
        ret == false
    }

}
