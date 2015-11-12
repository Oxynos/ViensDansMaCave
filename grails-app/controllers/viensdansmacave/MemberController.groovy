package viensdansmacave

import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

import grails.transaction.Transactional

@Transactional(readOnly = true)
class MemberController {

    MemberService memberService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.list(params), model: [memberInstanceCount: Member.count()]
    }

    def show(Member memberInstance) {
        respond memberInstance
    }

    @Secured('isAuthenticated()')
    def showSimpleAccount() {
        def member = springSecurityService.currentUser
        render(view: 'showSimpleAccount', model:[member: member])
    }

    def create() {
        respond new Member(params)
    }

    @Secured('permitAll')
    def createSimpleAccount() {
        render(view: 'createSimpleAccount')
    }

    @Transactional
    def save(Member memberInstance) {
        if (memberInstance == null) {
            notFound()
            return
        }

        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view: 'create'
            return
        }

        memberInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])
                redirect memberInstance
            }
            '*' { respond memberInstance, [status: CREATED] }
        }
    }

    @Secured('permitAll')
    def saveSimpleAccount() {
        def username = params.username
        def password = params.password
        def confirmation = params.confirmation
        def retourCreation = new Member()

        if(confirmation.equals(password)){
            retourCreation = memberService.saveSimpleAccount(username, password)
        }
        else
        {
            retourCreation.errors.rejectValue(
                    'password',
                    'member.password.doesnotmatch')
        }

        if (retourCreation.hasErrors()) {
            render(view: 'createSimpleAccount',model:[retourCreation: retourCreation])
        }
        else
        {
            render(view: 'saveSimpleAccount',model:[retourCreation: retourCreation.username])
        }
    }

    def edit(Member memberInstance) {
        respond memberInstance
    }

    @Secured('isAuthenticated()')
    def editSimpleAccount() {
        def member = springSecurityService.currentUser
        render(view: 'editSimpleAccount', model:[member: member])
    }

    @Transactional
    def update(Member memberInstance) {
        if (memberInstance == null) {
            notFound()
            return
        }

        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view: 'edit'
            return
        }

        memberInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect memberInstance
            }
            '*' { respond memberInstance, [status: OK] }
        }
    }

    @Secured('isAuthenticated()')
    def updateSimpleAccount(Member member, String passwordConfirm) {

        if(!passwordConfirm.equals(member.password)) {
            member.errors.rejectValue(
                    'password',
                    'member.password.doesnotmatch')
        }

        if (member.hasErrors()) {
            respond member.errors, view: 'editSimpleAccount', model:[member: member, passwordConfirm: passwordConfirm]
            return
        }

        memberService.updateSimpleAccount(member)
        flash.message = "Votre compte a bien été mis à jour"
        redirect action:"showSimpleAccount"
    }

    @Transactional
    def delete(Member memberInstance) {

        if (memberInstance == null) {
            notFound()
            return
        }

        memberInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    @Secured('isAuthenticated()')
    def deleteSimpleAccount() {
        def member = springSecurityService.currentUser
        memberService.deleteSimpleAccount(member)

        SecurityContextHolder.clearContext()

        render(view: 'deleteSimpleAccount')

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
