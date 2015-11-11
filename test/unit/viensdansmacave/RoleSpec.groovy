package viensdansmacave

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class RoleSpec extends Specification {

    Role role;

    def setup() {
        role = new Role()
    }

    def cleanup() {
    }

    void "test constraints for a valid role"() {
        given: "a role initialized with a non blank and unique authority"
        role.authority = testAuthority

        expect: "the role is valid"
        role.validate() == true

        where:
        testAuthority | _
        "basic"       | _
        "admin"       | _
    }

    void "test constraints for an invalid role"() {
        given: "a role initialized with a blank or null authority"
        role.authority = testAuthority

        expect: "the role is valid"
        role.validate() == false

        where:
        testAuthority | _
        ""            | _
        null          | _
    }

    void "test the hashCode method returns the correct value"() {
        given: "a Role instance"
        role.authority = null

        when: "hashCode method is called"
        def res = role.hashCode()

        then: "it returns the correct value"
        res == 0

        when: "hashCode method is called with authority not null"
        role.authority = new String("test")
        res = role.hashCode()

        then: "it returns the correct value"
        res == 3556498
    }

    void "test the equals method returns the correct value"() {
        given: "a set of Role instances"
        Role role1 = new Role(authority: "test")
        Role role2 = new Role(authority: "testbis")
        Role role3 = new Role(authority: "test")

        when: "equals method is called with the same instance"
        def res = role1.equals(role1)

        then: "it returns true"
        res

        when: "equals method is called with another instance"
        res = role1.equals(role2)

        then: "it returns false"
        !res

        when: "equals method is called with another equivalent instance"
        res = role1.equals(role3)

        then: "it returns true"
        res

        when: "equals method is called with another object"
        res = role1.equals(new String())

        then: "it returns false"
        !res
    }

    void "test toString method returns the correct value" () {
        given: "a Role instance"
        role.authority = new String("test")

        when: "toString method is called"
        def res = role.toString()

        then: "it returns the correct value"
        res == "test"
    }
}
