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
}
