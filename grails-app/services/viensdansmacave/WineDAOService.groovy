package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class WineDAOService {

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
        def criteria = Wine.createCriteria()
        def res = criteria.list {
            if (name) {
                like 'name', "%${name}%"
            }
            if (year) {
                and {
                    eq 'year', year
                }
            }
        }
        res
    }
}
