package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarDAOService {

    def saveCellar(Cellar cellar) {
        def cellarSave = cellar.save(flush: true)
        cellarSave ?: cellar
    }

    def wineIsInCellar(Wine wine, Cellar cellar) {
        WineCellar.findByWineAndCellar(wine, cellar)
    }

    def removeWineFromCellar(WineCellar wineCellar) {
        try {
            wineCellar?.delete(flush: true)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
