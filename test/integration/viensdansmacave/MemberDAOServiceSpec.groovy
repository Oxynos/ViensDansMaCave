package viensdansmacave

import spock.lang.*

/**
 * Created by Stav on 03/10/2015.
 */
class MemberDAOServiceSpec extends Specification{

    MemberDAOService memberDAOService
    TestSetService testSetService

    void "Test the saveSimpleAccount method correctly persists a Member instance"() {
        given: "A valid member"
        def member = new Member("toto", "pass")
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
        Member.count() == 1
    }

    void "Test the deleteSimpleAccount method correctly delete a Member"() {
        given: "A Member from the test set"
        def memberId = testSetService.member1.id

        when: "The deleteSimpleAccount method is called"
        memberDAOService.deleteSimpleAccount(testSetService.member1)

        then: "The Member instance is correctly deleted"
        Member.get(memberId) == null
    }
}
