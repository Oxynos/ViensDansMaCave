package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cellar)
class CellarSpec extends Specification {

    Cellar cellar

    def setup() {
        cellar = new Cellar()
    }

    def cleanup() {
    }

    void "test constraints for a valid member"() {
        given: "a cellar initialized"
        cellar.rate = testRate

        expect: "the cellar is valid"
        cellar.validate() == true

        where:
        testRate | _
        1.0      | _
        2.5      | _
        5.0      | _
    }
}
