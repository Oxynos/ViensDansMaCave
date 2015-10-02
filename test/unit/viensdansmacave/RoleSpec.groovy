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
        testAuthority   |   _
        "basic"         |   _
        "admin"         |   _
    }

    void "test constraints for an invalid role"() {
        given: "a role initialized with a blank or null authority"
        role.authority = testAuthority

        expect: "the role is valid"
        role.validate() == false

        where:
        testAuthority   |   _
        ""              |   _
        null            |   _
    }
}
