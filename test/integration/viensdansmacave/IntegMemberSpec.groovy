package viensdansmacave


import spock.lang.*

/**
 *
 */
class IntegMemberSpec extends Specification {

    TestSetService testSetService

    def setup() {
    }

    def cleanup() {
    }

    void "test getAuthorities method returns the correct set"() {
        given: "a test set"
        testSetService

        when: "getAuthorities method is called"
        def res = testSetService.member1.getAuthorities()

        then: "it returns the correct set"
        res.size() == 1
    }

    void "test the beforeUpdate method encode the password if it is modified" () {
        given: "a test set"
        testSetService

        when: "member's password changes and beforeUpdate method is called on save call"
        Member member = Member.findByUsername("toto")
        member.password = "nouveaumdp"
        member = member.save(flush: true)

        then: "the password is encoded"
        member.password != "nouveaumdp"
    }

    void "test the beforeUpdate method doesn't encode the password if it isn't modified" () {
        given: "a test set"
        testSetService

        when: "beforeUpdate method is called on save call"
        Member member = Member.findByUsername("toto")
        def password = member.password
        member = member.save(flush: true)

        then: "the password is unchanged"
        member.password == password
    }
}
