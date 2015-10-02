package viensdansmacave

import grails.transaction.Transactional

@Transactional
class MemberService {

    def serviceMethod() {

    }

    def saveSimpleAccount(String username, String password) {

        def member = new Member(username, password)
        member.email = null
        member.age = null
        member.city = null
        member.country = null
        member.accountExpired = false
        member.accountLocked = false
        member.passwordExpired = false

        def simpleAccountSave = member.save(flush:true)
        simpleAccountSave ?: member


    }
}
