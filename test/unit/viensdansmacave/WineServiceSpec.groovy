package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification
import wine.WineColor

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

    void "test getting wine by criteria with 2 params"() {
        when: "the service is called"
        service.getWinesByNameAndYear("test", 1982)

        then: "the service use the good DAO method with correct parameters"
        1 * wineDAOService.getWinesByNameYear("test", 1982)
    }

    void "test getting wine by criteria with 2 params optional"() {
        when: "the service is called"
        service.getWinesByNameAndYear(null, 0)

        then: "the service use the good DAO method with correct parameters"
        0 * wineDAOService.getWinesByNameYear(_, _)
    }

    void "Test the save method calls the WineDAOService method"() {
        when: "The save method is called"
        service.save(Mock(Wine))

        then: "The save method of WineDAOService is called"
        1 * wineDAOService.save(_)
    }

}
