package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification
import wine.WineColor

import java.time.LocalDate

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Wine)
class WineSpec extends Specification {

    Wine wine

    def setup() {
        wine = new Wine()
    }

    void "test a valid wine"() {
        given: "a wine with a name, a year and a color"
        wine.name = testName
        wine.year = testYear
        wine.color = testColor

        expect: "a valid wine"
        wine.validate() == true

        where:
        testName       | testYear             | testColor
        "valid"        | 1982                 | WineColor.RED
        "Margaux"      | 2009                 | WineColor.GREY
        "Grand Pomard" | LocalDate.now().year | WineColor.WHITE
        "Grand Pomard" | 0                    | WineColor.WHITE
    }

    void "test an invalid wine"() {
        given: "a wine"
        wine.name = testName
        wine.year = testYear
        wine.color = testColor

        expect: "an invalid wine"
        wine.validate() == false

        where:
        testName  | testYear                 | testColor
        ""        | 1982                     | WineColor.WHITE
        null      | 1982                     | WineColor.WHITE
        "chateau" | null                     | WineColor.WHITE
        "chateau" | LocalDate.now().year + 1 | WineColor.WHITE
        "chateau" | -1                       | WineColor.WHITE
        "chateau" | 1982                     | null
    }
}
