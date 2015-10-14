package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarService {

    CellarDAOService cellarDAOService

    def wineIsInCellar(Wine wine, Cellar cellar) {
        cellarDAOService.wineIsInCellar(wine, cellar)
    }

    def removeWineFromCellar(Wine wine, Cellar cellar) {
        if (wineIsInCellar(wine, cellar)) {
            cellarDAOService.removeWineFromCellar(wine, cellar)
        } else {
            false
        }
    }

    def addWineInCellar(Wine wine, Cellar cellar) {
        def wineCellar = new WineCellar()
        wineCellar.wine = wine
        wineCellar.cellar = cellar
        wineCellar.quantity = 1
        cellarDAOService.addWineInCellar(wineCellar)
    }

    def insertCellarForMember(Member member) {
        def cellar = new Cellar();
        cellarDAOService.saveCellar(cellar);
        member.cellar = cellar
    }
}
