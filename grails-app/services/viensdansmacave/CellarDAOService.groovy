package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarDAOService {

    def saveCellar(Cellar cellar) {
        def cellarSave = cellar.save(flush:true)
        cellarSave ?: cellar
    def wineIsInCellar(Wine wine, Cellar cellar) {
        def wineCellar = WineCellar.findByWineAndCellar(wine, cellar)
        wineCellar ? true : false
    }

    def removeWineFromCellar(Wine wine, Cellar cellar) {
        def wineCellar = WineCellar.findByWineAndCellar(wine, cellar)
        try {
            wineCellar?.delete(flush: true)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
