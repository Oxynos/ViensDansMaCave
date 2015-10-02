package viensdansmacave

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MemberSpec extends Specification {

    Member member;

    def setup() {
        member = new Member();
    }

    def cleanup() {
    }

    void "test constraints for a valid member"() {
        given : "a member initialized with a non blank and unique username, a non blank password, a valid email, an age, a country and a city"
        member.username = testUserName
        member.password = testPassword
        member.email = testEmail
        member.age = testAge
        member.country = testCountry
        member.city = testCity

        expect : "the member is valid"
        member.validate() == true

        where :
        testUserName    |  testPassword |  testEmail           |   testAge     |   testCountry  |  testCity
        "a name"        |  "123 pwd"    |  "member@email.com"  |   23          |   "a country"  | "a city"
        "a name 2"      |  "123 pwd"    |  ""                  |   23          |   "a country"  | "a city"
        "a name 3"      |  "123 pwd"    |  null                |   23          |   "a country"  | "a city"
        "a name 4"      |  "123 pwd"    |  "member@email.com"  |   null        |   "a country"  | "a city"
        "a name 5"      |  "123 pwd"    |  "member@email.com"  |   23          |   ""           | "a city"
        "a name 6"      |  "123 pwd"    |  "member@email.com"  |   23          |   null         | "a city"
        "a name 7"      |  "123 pwd"    |  "member@email.com"  |   23          |   "a country"  | ""
        "a name 8"      |  "123 pwd"    |  "member@email.com"  |   23          |   "a country"  | null


    }

    void "test constraints for an invalid member"() {
        given : "a member initialized with a blank or null username, a blank or null password, a invalid email"
        member.username = testUserName
        member.password = testPassword
        member.email = testEmail
        member.age = testAge
        member.country = testCountry
        member.city = testCity

        expect : "the member is invalid"
        member.validate() == false

        where :
        testUserName    |  testPassword |  testEmail           |   testAge     |   testCountry  |  testCity
        ""              |  "123 pwd"    |  "member@email.com"  |   23          |   "a country"  | "a city"
        null            |  "123 pwd"    |  ""                  |   23          |   "a country"  | "a city"
        "a name"        |  ""           |  "member@email.com"  |   23          |   "a country"  | "a city"
        "a name 2"      |  null         |  "member@email.com"  |   23          |   "a country"  | "a city"
        "a name 3"      |  "123 pwd"    |  "not an email"      |   23          |   "a country"  | "a city"


    }
}
