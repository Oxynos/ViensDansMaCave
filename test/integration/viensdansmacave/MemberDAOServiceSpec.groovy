package viensdansmacave

import spock.lang.Specification

/**
 * Created by Thomas Zoratto on 03/10/2015.
 */
class MemberDAOServiceSpec extends Specification{

    MemberDAOService memberDAOService
    TestSetService testSetService

    void "Test the saveSimpleAccount method correctly persists a Member instance"() {
        given: "A valid member"
        def member = new Member("testSave", "pass")
        member.email = null
        member.birthday = null
        member.city = null
        member.country = null
        member.accountExpired = false
        member.accountLocked = false
        member.passwordExpired = false

        when: "The saveSimpleAccount method is called"
        memberDAOService.saveSimpleAccount(member)

        then: "The Member instance is correctly persisted"
        member.id != null
    }

    void "Test the deleteSimpleAccount method correctly delete a Member"() {
        given: "A Member from the test set"
        def memberId = testSetService.member2.id

        when: "The deleteSimpleAccount method is called"
        memberDAOService.deleteSimpleAccount(testSetService.member2)

        then: "The Member instance is correctly deleted"
        Member.get(memberId) == null
    }

    void "Test that when a Member is deleted is cellar is deleted"() {
        given: "A Member from the test set and his cellar"
        def member = testSetService.member2
        def cellar = member.cellar

        when: "The member is deleted"
        memberDAOService.deleteSimpleAccount(testSetService.member2)

        then: "His cellar is deleted"
        Cellar.findByMember(member) == null
        WineCellar.findByCellar(cellar) == null
    }

    void "test that when the saveSimpleAccount method is called on an existing Member the Member is updated"() {
        given: "A Member from the test set"
        def member = testSetService.member1
        member.username = "totobis"
        member.email = "test@test.com"
        member.birthday = new Date(1990,3,12)
        member.country = "FRANCE"
        member.city = "TOULOUSE"

        when: "The member is saved with new informations"
        memberDAOService.saveSimpleAccount(member)

        then: "The member is updated"
        testSetService.member1 == member
    }

}
