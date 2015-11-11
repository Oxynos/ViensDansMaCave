package viensdansmacave

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([MemberRole, Member, Role])
class MemberRoleSpec extends Specification {

    MemberRole memberRole;
    Member member1, member2
    Role role1, role2

    def setup() {
        memberRole = new MemberRole()
        member1 = new Member()
        member2 = new Member()
        member1.id = 1
        member2.id = 2
        role1 = new Role()
        role2 = new Role()
        role1.id = 1
        role2.id = 2
    }

    void "test constraints for a valid MemberRole"() {
        given: "a MemberRole initialized with valid member and role instances"
        Member member
        Role role
        if (memberId) {
            member = new Member()
            if (memberId != 0) {
                member.id = memberId
            } else {
                member.id = null
            }
        }
        role = new Role()
        role.id = 1
        memberRole.member = member
        memberRole.role = role

        expect: "the memberRole is valid"
        memberRole.validate() == true

        where:
        memberId | _
        1        | _
    }

    void "test constraints for an invalid MemberRole"() {
        given: "a MemberRole initialized with invalid member and role instances"
        Member member = null
        Role role
        if (memberId) {
            member = new Member()
            if (memberId != 0) {
                member.id = memberId
            } else {
                member.id = null
            }
        }
        role = new Role()
        role.id = 1
        memberRole.member = member
        memberRole.role = role

        role.id = 1

        expect: "the memberRole is invalid"
        memberRole.validate() == false

        where:
        memberId | _
        null     | _
        0        | _

    }

    void "test the equals method returns the correct value" () {
        given: "a set of memberRole instances"
        MemberRole mr1 = new MemberRole()
        mr1.member = member1
        mr1.role = role1
        MemberRole mr2 = new MemberRole()
        mr2.member = member2
        mr2.role = role2
        MemberRole mr3 = new MemberRole()
        mr3.member = member2
        mr3.role = role1
        MemberRole mr4 = new MemberRole()
        mr4.member = member1
        mr4.role = role2

        when: "equals method is called with another object"
        def res = mr1.equals(new String())

        then: "it returns false"
        !res

        when: "equals method is called with the same object"
        res = mr1.equals(mr1)

        then: "it returns true"
        res

        when: "equals method is called with a different object"
        res = mr1.equals(mr2)

        then: "it returns false"
        !res

        when: "equals method is called with a different object"
        res = mr1.equals(mr3)

        then: "it returns false"
        !res

        when: "equals method is called with a different object"
        res = mr1.equals(mr4)

        then: "it returns false"
        !res
    }

    void "test the hashCode method returns the correct value" () {
        given: "a memberRole instance"
        MemberRole mr = new MemberRole()

        when: "hashCode method is called with this empty instance"
        def res = mr.hashCode()

        then: "it returns the correct value"
        res == 17

        when: "hashCode method is called with a not empty instance"
        mr.member = new Member(id:1)
        mr.role = new Role(id:1)
        res = mr.hashCode()

        then: "it returns the correct value"
        res == 23273
    }

    void "test the create method correctly persists an instance" () {
        when: "create method is called with valid instances"
        def res = memberRole.create(member1, role1)

        then: "the instance is correctly persisted"
        res.id != null
    }

    void "test the remove method returns the correct value" () {
        when: "the remove method is called without parameter"
        def res = MemberRole.remove(null, null)

        then: "it returns false"
        !res

        when: "the remove method is called without all parameter"
        res = MemberRole.remove(null, role1)

        then: "it returns false"
        !res

        when: "the remove method is called without all parameter"
        res = MemberRole.remove(member1, null)

        then: "it returns false"
        !res

        when: "the remove method is called with valid instances"
        res = MemberRole.remove(member1, role1, true)

        then: "it returns false"
        !res
    }

    void "test the removeAll with Member parameter method returns the correct value" () {
        when: "the removeAll method is called without parameter"
        Member member = null
        def res = MemberRole.removeAll(member as Member)

        then: "it returns false"
        !res
    }

    void "test the removeAll with Role parameter method returns the correct value" () {
        when: "the removeAll method is called without parameter"
        Role role = null
        def res = MemberRole.removeAll(role as Role)

        then: "it returns false"
        !res
    }
}
