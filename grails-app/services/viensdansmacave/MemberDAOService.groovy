package viensdansmacave

import grails.transaction.Transactional

@Transactional
class MemberDAOService {

    def saveSimpleAccount(Member member) {
        def simpleAccountSave = member.save(flush:true)
        simpleAccountSave ?: member
    }
}
