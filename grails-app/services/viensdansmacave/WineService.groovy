package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class WineService {

    WineDAOService wineDAOService

    def findWineNames() {
        wineDAOService.findWineNames()
    }

    def findWineYears() {
        wineDAOService.findWineYears()
    }

    /**
     * Search wines by optional criteria but at least one of them needed
     * @param name nullable for optional
     * @param year 0 for optional
     * @return list of wine or nothing if two params are optionals
     */
    def getWinesByNameAndYear(String name, int year) {
        if (name || year) {
            wineDAOService.getWinesByNameYear(name, year)
        }
    }

    def find(long id) {
        wineDAOService.find(id)
    }

}
