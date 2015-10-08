package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MemberService)
class MemberServiceSpec extends Specification {

    MemberDAOService memberDAOService

    def setup() {
        memberDAOService = Mock(MemberDAOService)
        service.memberDAOService = memberDAOService
    }

    def cleanup() {
    }

    void "Test the saveSimpleAccount method calls the MemberDAOService method"() {
        when: "The saveSimpleAccount method is called with valid parameters"
        service.saveSimpleAccount('toto','pass')

        then: "The saveSimpleAccount method of the MemberDAOService is called"
        1 * memberDAOService.saveSimpleAccount(_)
    }
}