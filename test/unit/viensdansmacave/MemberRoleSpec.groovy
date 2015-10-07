package viensdansmacave

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MemberRoleSpec extends Specification {

    MemberRole memberRole;

    def setup() {
        memberRole = new MemberRole()
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
