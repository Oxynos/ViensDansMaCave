package viensdansmacave

import spock.lang.*

/**
 * Created by Thomas Zoratto on 03/10/2015.
 */
class MemberDAOServiceSpec extends Specification{

    MemberDAOService memberDAOService

    void "Test the saveSimpleAccount method correctly persists a Member instance"() {
        given: "A valid member"
        def member = new Member("toto", "pass")
        member.email = null
        member.dateNaissance = null
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
}
