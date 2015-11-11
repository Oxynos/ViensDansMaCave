package viensdansmacave

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([Member, MemberRole])
class MemberSpec extends Specification {

    Member member;

    def setup() {
        member = new Member();
    }

    void "test constraints for a valid member"() {
        given: "a member initialized with a non blank and unique username, a non blank password, a valid email, an age, a country and a city"
        member.username = testUserName
        member.password = testPassword
        member.email = testEmail
        member.birthday = testDateNaissance
        member.country = testCountry
        member.city = testCity

        expect: "the member is valid"
        member.validate() == true

        where:
        testUserName | testPassword | testEmail          | testDateNaissance   | testCountry | testCity
        "a name"     | "123 pwd"    | "member@email.com" | new Date(90, 6, 18) | "a country" | "a city"
        "a name 2"   | "123 pwd"    | ""                 | new Date(90, 6, 18) | "a country" | "a city"
        "a name 3"   | "123 pwd"    | null               | new Date(90, 6, 18) | "a country" | "a city"
        "a name 4"   | "123 pwd"    | "member@email.com" | null                | "a country" | "a city"
        "a name 5"   | "123 pwd"    | "member@email.com" | new Date(90, 6, 18) | ""          | "a city"
        "a name 6"   | "123 pwd"    | "member@email.com" | new Date(90, 6, 18) | null        | "a city"
        "a name 7"   | "123 pwd"    | "member@email.com" | new Date(90, 6, 18) | "a country" | ""
        "a name 8"   | "123 pwd"    | "member@email.com" | new Date(90, 6, 18) | "a country" | null


    }

    void "test constraints for an invalid member"() {
        given: "a member initialized with a blank or null username, a blank or null password, a invalid email"
        member.username = testUserName
        member.password = testPassword
        member.email = testEmail
        member.birthday = testDateNaissance
        member.country = testCountry
        member.city = testCity

        expect: "the member is invalid"
        member.validate() == false

        where:
        testUserName | testPassword | testEmail          | testDateNaissance    | testCountry | testCity
        ""           | "123 pwd"    | "member@email.com" | new Date(90, 6, 18)  | "a country" | "a city"
        null         | "123 pwd"    | ""                 | new Date(90, 6, 18)  | "a country" | "a city"
        "a name"     | ""           | "member@email.com" | new Date(90, 6, 18)  | "a country" | "a city"
        "a name 2"   | null         | "member@email.com" | new Date(90, 6, 18)  | "a country" | "a city"
        "a name 3"   | "123 pwd"    | "not an email"     | new Date(90, 6, 18)  | "a country" | "a city"
        "a name 4"   | "123 pwd"    | "member@email.com" | new Date()           | "a country" | "a city"
        "a name 5"   | "123 pwd"    | "member@email.com" | new Date(200, 6, 18) | "a country" | "a city"

    }

    void "test the hashCode method returns the correct value"() {
        given: "a member"
        member.username = "toto"

        when: "the hashCode method is called"
        def res = member.hashCode()

        then: "it returns the correct value"
        res == 3566134

        when: "the hashCode method is called with no username"
        member.username = null
        res = member.hashCode()

        then: "it returns 0"
        res == 0
    }

    void "test the equals method returns the correct value"() {
        given: "two member with the same username"
        member.username = "toto"
        def member2 = new Member()
        member2.username = "toto"

        when: "the equals method is called with these two members"
        def res = member.equals(member2)

        then: "it returns true"
        res

        when: "the equals method is called with the same member"
        res = member.equals(member)

        then: "it returns true"
        res

        when: "the equals method is called with an other member"
        member2.username = "titi"
        res = member.equals(member2)

        then: "it returns false"
        !res

        when: "the equals method is called with an other object"
        res = member.equals(new String())

        then: "it returns false"
        !res
    }

    void "test the toString method returns the correct value"() {
        given: "a member"
        member.username = "toto"

        when: "the toString method is called"
        def res = member.toString()

        then: "it returns the correct value"
        res == "toto"
    }
}
