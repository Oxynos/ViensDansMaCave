package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarService {

    CellarDAOService cellarDAOService

    def wineIsInCellar(Wine wine, Cellar cellar) {
        cellarDAOService.wineIsInCellar(wine, cellar)
    }

    def removeWineFromCellar(Wine wine, Cellar cellar) {
        WineCellar wineCellar = wineIsInCellar(wine, cellar)
        if (wineCellar != null) {
            cellarDAOService.removeWineFromCellar(wineCellar)
        } else {
            false
        }
    }

    def addWineInCellar(Wine wine, Cellar cellar, int quantity) {
        def wineCellar = new WineCellar()
        wineCellar.wine = wine
        wineCellar.cellar = cellar
        wineCellar.quantity = quantity
        cellarDAOService.addWineInCellar(wineCellar)
    }

    def insertCellarForMember(Member member) {
        def cellar = new Cellar();
        cellarDAOService.saveCellar(cellar);
        member.cellar = cellar
    }

    def wineRanking() {
        cellarDAOService.wineRanking()
    }

    def increaseQuantity(WineCellar wineCellar) {
        wineCellar.quantity++
        cellarDAOService.addWineInCellar(wineCellar)
    }

    def reduceQuantity(WineCellar wineCellar) {
        if (wineCellar.quantity > 1) {
            wineCellar.quantity--
            cellarDAOService.addWineInCellar(wineCellar)
        } else {
            cellarDAOService.removeWineFromCellar(wineCellar)
        }
    }
}
