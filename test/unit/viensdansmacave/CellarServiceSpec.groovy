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

    void "test adding correctly a wine in a cellar"() {
        given: "A wine instance and a cellar"
        Wine wine = Mock(Wine)
        Cellar cellar = Mock(Cellar)

        when : "The removeWineFromCellar method is called"
        service.addWineInCellar(wine, cellar, 1)

        then : "The removeWineFromCellar method of the CellarDAOService is called"
        1 * cellarDAOService.addWineInCellar(_)
    }

    void "Test the insertCellarForMember method"() {
        given : "A member instance and a cellar instance"
        Member member = new Member("test", "test")

        when : "The insertCellarForMember method is called"
        service.insertCellarForMember(member)

        then : "member.cellar is initialized"
        member.cellar instanceof Cellar
    }

    void "Test that the wineRanking method calls the DAO method"() {
        when: "The wineRanking method is called"
        service.wineRanking()

        then: "The DAO method is called"
        1 * cellarDAOService.wineRanking()
    }

    void "Test that the increaseQuantity method calls the DAO method"() {
        given: "A WineCellar instance"
        WineCellar wineCellar = new WineCellar(wine: Mock(Wine), cellar: Mock(Cellar), quantity:1)

        when: "The increaseQuantity method is called"
        service.increaseQuantity(wineCellar)

        then: "The DAO method is called"
        1 * cellarDAOService.addWineInCellar(_)
    }

    void "Test that the reduceQuantity method calls the correct DAO method"() {
        given: "A WineCellar instance"
        WineCellar wineCellar = new WineCellar(wine: Mock(Wine), cellar: Mock(Cellar), quantity:1)

        when: "The reduceQuantity method is called with quantity set to 1"
        service.reduceQuantity(wineCellar)

        then: "The correct DAO method is called"
        1 * cellarDAOService.removeWineFromCellar(_)

        when: "The reduceQuantity method is called with quantity set to more than 1"
        wineCellar.quantity = 2
        service.reduceQuantity(wineCellar)

        then: "The correct DAO method is called"
        1 * cellarDAOService.addWineInCellar(_)
    }

}
