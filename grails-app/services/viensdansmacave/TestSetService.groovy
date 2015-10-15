package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class TestSetService {

    Member member1

    Cellar cellar1

    Wine wine1
    Wine wine2

    WineCellar wineCellar1

    def createTestSet() {
        member1 = new Member('toto', 'mdp')
        member1.email = null
        member1.birthday = null
        member1.city = null
        member1.country = null
        member1.accountExpired = false
        member1.accountLocked = false
        member1.passwordExpired = false
        member1.save()

        cellar1 = new Cellar(rate:0, member:member1).save()

        wine1 = new Wine(name:"un vin",year:2000,color:WineColor.RED).save()
        wine2 = new Wine(name:"un autre vin",year:2000,color:WineColor.RED).save()

        wineCellar1 = new WineCellar(wine: wine1, cellar: cellar1, quantity:1).save()

        cellar1.addToWineCellars(wineCellar1).save()
        wine1.addToWineCellars(wineCellar1).save()
    }
}
