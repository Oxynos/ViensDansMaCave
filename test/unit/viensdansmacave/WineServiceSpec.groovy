package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(WineService)
class WineServiceSpec extends Specification {

    WineDAOService wineDAOService

    def setup() {
        wineDAOService = Mock(WineDAOService)
        service.wineDAOService = wineDAOService
    }

    def cleanup() {
    }

    void "test getting all names distinct"() {
        when: "the service is called"
        service.findWineNames()

        then: "the service use the good DAO method"
        1 * wineDAOService.findWineNames()
    }

    void "test getting all years distinct"() {
        when: "the service is called"
        service.findWineYears()

        then: "the service use the good DAO method"
        1 * wineDAOService.findWineYears()
    }
}
