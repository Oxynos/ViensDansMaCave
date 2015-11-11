package viensdansmacave

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cellar)
class CellarSpec extends Specification {

    void "test la validite d'une cave"() {

        given:"a member and a cellar initialized"
        Member member = Mock(Member)
        Cellar cellar = new Cellar(member: member)
        cellar.rate = testRate

        expect: "la cave est valide"
        cellar.validate() == true

        and: "les propietes de la cave sont valides"
        cellar.member == member

        where:
        testRate | _
        0.0      | _
        2.5      | _
        5.0      | _


    }
}
