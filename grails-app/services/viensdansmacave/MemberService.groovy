package viensdansmacave

import grails.transaction.Transactional

@Transactional
class MemberService {

    MemberDAOService memberDAOService
    CellarService cellarService

    def saveSimpleAccount(String username, String password) {

        def member = new Member(username, password)
        member.email = null
        member.birthday = null
        member.city = null
        member.country = null
        member.accountExpired = false
        member.accountLocked = false
        member.passwordExpired = false
        cellarService.insertCellarForMember(member)

        memberDAOService.saveSimpleAccount(member)

    }

    def deleteSimpleAccount(Member member) {
        memberDAOService.deleteSimpleAccount(member)
    }

    def updateSimpleAccount(Member member) {
        memberDAOService.saveSimpleAccount(member)
    }
}
