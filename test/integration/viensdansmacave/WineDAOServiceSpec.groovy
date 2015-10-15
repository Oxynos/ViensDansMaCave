package viensdansmacave


import spock.lang.*

/**
 *
 */
class WineDAOServiceSpec extends Specification {

    WineDAOService wineDAOService
    TestSetService testSetService

    void "test findWineName method"() {
        given: "A test set"
        testSetService

        when: "the method is called"
        def res = wineDAOService.findWineNames()

        then: "there are two string"
        res.size() == 2
    }

    void "test findWineYear method"() {
        given: "A test set"
        testSetService

        when: "the method is called"
        def res = wineDAOService.findWineYears()

        then: "there is one int"
        res.size() == 1
    }

    void "test getWinesByNameYear method"() {
        given: "A test set"
        testSetService

        when: "the method is called with two params"
        def res = wineDAOService.getWinesByNameYear("un vin", 2000)

        then: "return the good wine"
        res.size() == 1

        when: "the method is called with year"
        def res2 = wineDAOService.getWinesByNameYear(null, 2000)

        then: "return the good wine"
        res2.size() == 2

        when: "the method is called with year"
        def res3 = wineDAOService.getWinesByNameYear("vin", 0)

        then: "return the good wine"
        res3.size() == 2
    }
}
