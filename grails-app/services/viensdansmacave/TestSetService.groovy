package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class TestSetService {

    Member member1

    Cellar cellar1

    Wine wine1, wine2, wine3

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

        wine1 = new Wine(name:"Merlot", year:2013, color:WineColor.RED).save()
        wine2 = new Wine(name:"Sauternes", year:2008, color:WineColor.WHITE).save()
        wine3 = new Wine(name:"Côtes de Provence", year:2014, color:WineColor.ROSE).save()

        wineCellar1 = new WineCellar(wine: wine1, cellar: cellar1, quantity:1).save()

        cellar1.addToWineCellars(wineCellar1).save()
        wine1.addToWineCellars(wineCellar1).save()

        wineCellar1 = new WineCellar(wine: wine3, cellar: cellar1, quantity:4).save()

        cellar1.addToWineCellars(wineCellar1).save()
        wine3.addToWineCellars(wineCellar1).save()
    }
}
