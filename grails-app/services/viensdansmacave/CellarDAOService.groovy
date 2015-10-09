package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarDAOService {

    def saveCellar(Cellar cellar) {
        def cellarSave = cellar.save(flush:true)
        cellarSave ?: cellar
    }
}
