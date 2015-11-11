package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CellarService)
class CellarServiceSpec extends Specification {

    CellarDAOService cellarDAOService

    def setup() {
        cellarDAOService = Mock(CellarDAOService) {
            wineIsInCellar(_,_) >> Mock(WineCellar)
        }
        service.cellarDAOService = cellarDAOService
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

    void "Test updating cellar rate"() {
        given : "A cellar"
        Cellar cellar = new Cellar()
        def newRate = 3.floatValue()

        when : "The updateRate method is called"
        service.updateRate(cellar)

        then: "the new rate is computed and the cellar is saved"
        1 * cellarDAOService.computeRateForCellar(cellar) >> newRate
        1 * cellarDAOService.saveCellar(_)
        cellar.rate == newRate
    }

    void "test getting the rate from a user for a cellar"() {
        given : "A cellar and a member"
        Cellar cellar = Mock()
        Member member = Mock()

        when : "The updateRate method is called"
        service.getRateByUserAndCellar(cellar, member)

        then: "the rates are getting from dao"
        1 * cellarDAOService.getRateByUserAndCellar(cellar, member)
    }

    void "test adding a rate for a cellar"() {
        given : "A cellar, a member and a memberCellarRate without errors"
        Cellar cellar = new Cellar()
        Member member = Mock()
        MemberCellarRate memberCellarRate = Mock(MemberCellarRate) {
            hasErrors() >> false
        }

        when : "The addRate method is called"
        service.addRateForCellar(cellar, member, 2)

        then: "the dao methods is called to save the new rate and the cellar rate is computed and saved"
        1 * cellarDAOService.getRateByUserAndCellar(_, _) >> memberCellarRate
        1 * cellarDAOService.addMemberRating(_) >> memberCellarRate
        1 * cellarDAOService.computeRateForCellar(cellar) >> 5.floatValue()
        1 * cellarDAOService.saveCellar(_)
    }

    void "test adding not correctly a rate for a cellar"() {
        given : "A cellar, a member and a memberCellarRate with errors"
        Cellar cellar = Mock(Cellar)
        Member member = Mock(Member)
        MemberCellarRate memberCellarRate = Mock(MemberCellarRate) {
            hasErrors() >> true
        }

        when : "The addRate method is called"
        service.addRateForCellar(cellar, member, 2)

        then: "the dao method is called to saved the new rate but no computing neither update for cellar rate"
        1 * cellarDAOService.addMemberRating(_) >> memberCellarRate
        0 * cellarDAOService.computeRateForCellar(cellar)
        0 * cellarDAOService.saveCellar(_)
    }

    void "Test that the cellarRanking method calls the DAO method"() {
        when: "The cellarRanking method is called"
        service.getCellarRanking()

        then: "The DAO method is called"
        1 * cellarDAOService.getBestCellars()
    }
}
