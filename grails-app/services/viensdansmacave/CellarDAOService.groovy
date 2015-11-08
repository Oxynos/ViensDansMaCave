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

    def addWineInCellar(WineCellar wineCellar) {
        def assoc = wineCellar.save(flush: true)
        assoc ?: wineCellar
    }

    def wineRanking() {
        WineCellar.createCriteria().list(){
            projections {
                groupProperty("wine")
                count("wine", 'myCount')
            }
            order('myCount','desc')
        }
    }

    def computeRateForCellar(Cellar cellar) {
        def query = MemberCellarRate.where {
            eq 'cellar', cellar
        }.projections {
            avg 'rate'
        }
        def rate = query.find() as float
        rate
    }

    def addMemberRating(MemberCellarRate rating) {
        def rate = rating.save(flush: true)
        rate ?: rating
    }

    def getRateByUserAndCellar(Cellar cellar, Member member) {
        MemberCellarRate.findByMemberAndCellar(member, cellar)
    }

    def getBestCellars() {
        Cellar.createCriteria().list(){
            order('rate', 'desc')
            maxResults 10
        }
    }
}
