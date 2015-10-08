package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class WineDAOService {

    def serviceMethod() {

    }

    def findWineNames() {
        def names = Wine.withCriteria {
            projections {
                distinct 'name'
            }
        }
        names
    }

    def findWineYears() {
        def years = Wine.withCriteria {
            projections {
                distinct 'year'
            }
        }
        years
    }

    def getWinesByNameYear(String name, int year) {
        def wines = Wine.withCriteria {
            if (name) {
                like 'name', "%${name}%"
            }
            if (year) {
                and {
                    equals 'year', year
                }
            }
        }
        wines
    }
}
