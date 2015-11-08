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
        def ret = cellarDAOService.wineIsInCellar(testSetService.wine1,testSetService.member1.cellar)

        then: "The wineIsInCellar method returns the WineCellar instance"
        (ret instanceof WineCellar) == true

        when: "The wineIsInCellar method is called with a vine which isn't in the cellar"
        ret = cellarDAOService.wineIsInCellar(testSetService.wine2,testSetService.member1.cellar)

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
        WineCellar.findByWineAndCellar(testSetService.wine1,testSetService.member1.cellar) == null
    }

    void "Test adding a wine in a cellar"() {
        given: "A test set and a wineCellar"
        testSetService
        def wineCellar = new WineCellar()
        wineCellar.wine = testSetService.wine2
        wineCellar.cellar = testSetService.member1.cellar
        wineCellar.quantity = 1

        when: "The addWineInCellar method is called with a wineCellar"
        cellarDAOService.addWineInCellar(wineCellar)

        then: "There are two wineCellars"
        WineCellar.count == 4
    }

}
