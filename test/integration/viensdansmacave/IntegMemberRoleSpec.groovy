package viensdansmacave


import spock.lang.*

/**
 *
 */
class IntegMemberRoleSpec extends Specification {

    TestSetService testSetService
    MemberRole memberRole

    void setup() {
        memberRole = new MemberRole()
    }

    void "test the get method returns a MemberRole instance"() {
        given: "a test set"
        testSetService

        when: "get method is called with valid parameters"
        def res = MemberRole.get(testSetService.member1.id, testSetService.role1.id)

        then: "a MemberRole instance is returned"
        res instanceof MemberRole
    }

    void "test the removeAll with a Member parameter method correctly remove an instance" () {
        given: "a test set"
        testSetService

        when: "removeAll is called with valid parameter"
        MemberRole.removeAll(testSetService.member1)

        then: "the instance is deleted"
        MemberRole.count() == 0
    }

    void "test the removeAll with a Role parameter method correctly remove an instance" () {
        given: "a test set"
        testSetService

        when: "removeAll is called with valid parameter"
        MemberRole.removeAll(testSetService.role1)

        then: "the instance is deleted"
        MemberRole.count() == 0
    }

    void "test that a MemberRole instance can't be duplicate" () {
        given: "a test set and a MemberRole instance"
        testSetService
        memberRole.member = testSetService.member1
        memberRole.role = testSetService.role1

        when: "validate method is called"
        def res = memberRole.validate()

        then: "it returns false"
        !res
    }
}
