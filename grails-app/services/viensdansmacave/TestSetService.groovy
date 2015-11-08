package viensdansmacave

import grails.transaction.Transactional
import wine.WineColor

@Transactional
class TestSetService {
    CellarService cellarService

    Member member1, member2, member3

    Cellar cellar1, cellar2, cellar3

    Wine wine1, wine2, wine3

    WineCellar wineCellar1, wineCellar2, wineCellar3

    MemberCellarRate rate1, rate2

    def createTestSet() {

        createMembers()

        wine1 = new Wine(name:"Merlot", year:2013, color:WineColor.RED).save()
        wine2 = new Wine(name:"Sauternes", year:2008, color:WineColor.WHITE).save()
        wine3 = new Wine(name:"Côtes de Provence", year:2014, color:WineColor.ROSE).save()

        wineCellar1 = new WineCellar(wine: wine1, cellar: member1.cellar, quantity:1).save()

        member1.cellar.addToWineCellars(wineCellar1).save()
        wine1.addToWineCellars(wineCellar1).save()

        wineCellar2 = new WineCellar(wine: wine3, cellar: member1.cellar, quantity:4).save()

        member1.cellar.addToWineCellars(wineCellar2).save()
        wine3.addToWineCellars(wineCellar2).save()

        wineCellar3 = new WineCellar(wine: wine3, cellar: member2.cellar, quantity:10).save()

        member2.cellar.addToWineCellars(wineCellar3).save()
        wine3.addToWineCellars(wineCellar3).save()

        rate1 = new MemberCellarRate(cellar: member1.cellar, member: member2, rate: 4).save()
        member1.cellar.addToMemberRating(rate1).save()
        member2.addToMemberRating(rate1).save()

        rate2 = new MemberCellarRate(cellar: member1.cellar, member: member3, rate: 2).save()
        member1.cellar.addToMemberRating(rate2).save()
        member3.addToMemberRating(rate2).save()
    }

    void createMembers() {
        member1 = new Member('toto', 'mdp')
        member1.email = null
        member1.birthday = null
        member1.city = null
        member1.country = null
        member1.accountExpired = false
        member1.accountLocked = false
        member1.passwordExpired = false
        cellarService.insertCellarForMember(member1)
        member1.save()

        member2 = new Member('cedric', 'mdp')
        member1.email = null
        member1.birthday = null
        member1.city = null
        member1.country = null
        member1.accountExpired = false
        member1.accountLocked = false
        member1.passwordExpired = false
        cellarService.insertCellarForMember(member2)
        member2.save()

        member3 = new Member('titi', 'mdp')
        member3.email = null
        member3.birthday = null
        member3.city = null
        member3.country = null
        member3.accountExpired = false
        member3.accountLocked = false
        member3.passwordExpired = false
        cellarService.insertCellarForMember(member3)
        member3.save()
    }
}
