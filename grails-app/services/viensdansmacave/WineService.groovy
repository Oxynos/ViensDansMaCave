package viensdansmacave

import grails.transaction.Transactional

@Transactional
class WineService {

    WineDAOService wineDAOService

    def serviceMethod() {

    }

    def findWineNames() {
        wineDAOService.findWineNames()
    }

    def findWineYears() {
        wineDAOService.findWineYears()
    }
}
