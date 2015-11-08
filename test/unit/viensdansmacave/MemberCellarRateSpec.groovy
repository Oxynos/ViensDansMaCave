package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MemberCellarRate)
class MemberCellarRateSpec extends Specification {

    MemberCellarRate memberCellarRate

    def setup() {
        memberCellarRate = new MemberCellarRate()
    }

    void "test a valid memberCellarRate"() {
        given: "a memberCellarRate with a cellar, a member and a rating"
        memberCellarRate.cellar = testCellar
        memberCellarRate.member = testMember
        memberCellarRate.rate = testRate

        expect: "a valid memberCellarRate"
        memberCellarRate.validate() == true

        where:
        testCellar   | testMember   | testRate
        Mock(Cellar) | Mock(Member) | 0
        Mock(Cellar) | Mock(Member) | 5
    }

    void "test an invalid memberCellarRate"() {
        given: "a memberCellarRate with a cellar, a member and a rating"
        memberCellarRate.cellar = testCellar
        memberCellarRate.member = testMember
        memberCellarRate.rate = testRate

        expect: "an invalid memberCellarRate"
        memberCellarRate.validate() == false

        where:
        testCellar   | testMember   | testRate
        Mock(Cellar) | Mock(Member) | -1
        Mock(Cellar) | Mock(Member) | 5.1
    }
}
