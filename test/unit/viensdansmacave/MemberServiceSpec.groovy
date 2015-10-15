package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MemberService)
class MemberServiceSpec extends Specification {

    MemberDAOService memberDAOService
    CellarService cellarService

    def setup() {
        memberDAOService = Mock(MemberDAOService)
        service.memberDAOService = memberDAOService
        cellarService = Mock(CellarService)
        service.cellarService = cellarService
    }

    def cleanup() {
    }

    void "Test the saveSimpleAccount method calls the MemberDAOService method"() {
        when: "The saveSimpleAccount method is called with valid parameters"
        service.saveSimpleAccount('toto','pass')

        then: "The saveSimpleAccount method of the MemberDAOService is called"
        1 * memberDAOService.saveSimpleAccount(_)
    }

    void "Test the deleteSimpleAccount method calls the MemberDAOService method"() {
        when : "The deleteSimpleAccount method is called with valid parameters"
        service.deleteSimpleAccount(Mock(Member))

        then: "The deleteSimpleAccount method of the MemberDAOService is called"
        1 * memberDAOService.deleteSimpleAccount(_)
    }
}
