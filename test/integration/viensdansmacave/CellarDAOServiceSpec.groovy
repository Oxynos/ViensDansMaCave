package viensdansmacave

import spock.lang.Specification

/**
 * Created by Thomas Zoratto on 08/10/2015.
 */
class CellarDAOServiceSpec extends Specification{

    CellarDAOService cellarDAOService
    TestSetService testSetService

    void "Test the wineIsInCellar method return the WineCellar instance if the wine is in the cellar"() {
        given: "A test set"
        testSetService

        when: "The wineIsInCellar method is called with a wine in the cellar"
        def ret = cellarDAOService.wineIsInCellar(testSetService.wine1,testSetService.cellar1)

        then: "The wineIsInCellar method returns the WineCellar instance"
        (ret instanceof WineCellar) == true

        when: "The wineIsInCellar method is called with a vine which isn't in the cellar"
        ret = cellarDAOService.wineIsInCellar(testSetService.wine2,testSetService.cellar1)

        then: "The wineIsInCellar method returns null"
        ret == null
    }

    void "Test the removeWineFromCellar method correctly remove a wine from a cellar"() {
        given: "A test set"
        testSetService

        when: "The removeWineFromCellar method is called with a wine in a cellar"
        def ret = cellarDAOService.removeWineFromCellar(testSetService.wineCellar1)

        then: "The removeWineFromCellar method returns true"
        ret == true

        and: "The wine isn't in the cellar anymore"
        WineCellar.findByWineAndCellar(testSetService.wine1,testSetService.cellar1) == null
    }

    void "Test adding a wine in a cellar"() {
        given: "A test set and a wineCellar"
        testSetService
        def wineCellar = new WineCellar()
        wineCellar.wine = testSetService.wine2
        wineCellar.cellar = testSetService.cellar1
        wineCellar.quantity = 1

        when: "The addWineInCellar method is called with a wineCellar"
        cellarDAOService.addWineInCellar(wineCellar)

        then: "There are two wineCellars"
        WineCellar.count == 3
    }

    void "Test that the wineRanking method returns the list of Wines with their ranking"() {
        given: "A test set"
        testSetService

        when: "The wineRanking method is called"
        def ret = cellarDAOService.wineRanking()

        then: "A list of wines with their ranking is returned"
        ret.size() == 2
        ret[0][1] == 1
    }

    void "test computing rate for a cellar"() {
        given: "A test set"
        testSetService

        when: "the method to compute is called for cellar 1"
        def rates = cellarDAOService.computeRateForCellar(testSetService.cellar1)

        then: "the rate average is 3: (4+2)/2"
        rates == 3.floatValue()
    }

    void "test adding a rate for a cellar"() {
        given: "A test set and a member rate"
        testSetService
        def memberRate = new MemberCellarRate()
        memberRate.rate = 5
        memberRate.member = testSetService.member1
        memberRate.cellar = testSetService.cellar2

        when: "adding the member rate"
        cellarDAOService.addMemberRating(memberRate)

        then: "There are 3 rates and one for cellar2"
        MemberCellarRate.count == 3
        def res = MemberCellarRate.findAllByCellar(testSetService.cellar2)
        res.size() == 1
    }

    void "test getting a rate from user for a cellar"() {
        given: "A test set"
        testSetService

        when: "getting the rate for a user and a cellar"
        def res = cellarDAOService.getRateByUserAndCellar(testSetService.cellar1, testSetService.member2)

        then: "the rate is 4"
        res.rate == 4
    }
}
