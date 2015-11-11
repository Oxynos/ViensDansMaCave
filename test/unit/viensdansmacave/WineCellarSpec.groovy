package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(WineCellar)
class WineCellarSpec extends Specification {

    WineCellar wineCellar

    def setup() {
        wineCellar = new WineCellar()
    }

    void "test a valid wineCellar"() {
        given: "a wineCellar with a cellar, a wine and a quantity"
        wineCellar.cellar = testCellar
        wineCellar.wine = testWine
        wineCellar.quantity = testQuantity

        expect: "a valid wineCellar"
        wineCellar.validate() == true

        where:
        testCellar   | testWine   | testQuantity
        Mock(Cellar) | Mock(Wine) | 1
        Mock(Cellar) | Mock(Wine) | 10
    }

    void "test an invalid wineCellar"() {
        given: "a wineCellar with a cellar, a wine and a quantity"
        wineCellar.cellar = testCellar
        wineCellar.wine = testWine
        wineCellar.quantity = testQuantity

        expect: "an invalid wineCellar"
        wineCellar.validate() == false

        where:
        testCellar   | testWine   | testQuantity
        Mock(Cellar) | Mock(Wine) | 0
        Mock(Cellar) | Mock(Wine) | -1
        null         | Mock(Wine) | 1
        Mock(Cellar) | null       | 1
    }
}
