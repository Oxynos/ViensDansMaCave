package viensdansmacave

import grails.transaction.Transactional

@Transactional
class CellarService {

    CellarDAOService cellarDAOService

    def insertCellarForMember(Member member) {
        def cellar = new Cellar();
        cellarDAOService.saveCellar(cellar);
        member.cellar = cellar
    }
}
