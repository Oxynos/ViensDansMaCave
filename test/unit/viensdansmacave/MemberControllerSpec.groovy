package viensdansmacave

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.Errors
import spock.lang.*

@TestFor(MemberController)
@Mock([Member,MemberService,SpringSecurityService])
class MemberControllerSpec extends Specification {

    MemberService memberServiceMock = Mock(MemberService) {saveSimpleAccount(_,_) >> Mock(Member) {hasErrors() >> false}}
    MemberService memberServiceMockErr = Mock(MemberService) {saveSimpleAccount(_,_) >> Mock(Member) {hasErrors() >> true}}
    SpringSecurityService springSecurityService = Mock(SpringSecurityService) {getCurrentUser() >> Mock(Member)}

    def populateValidParams(params) {
        assert params != null
        params["username"] = 'toto'
        params["password"] = 'pass'
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.memberInstanceList
        model.memberInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.memberInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def member = new Member()
        member.validate()
        controller.save(member)

        then: "The create view is rendered again with the correct model"
        model.memberInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        member = new Member(params)

        controller.save(member)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/member/show/1'
        controller.flash.message != null
        Member.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def member = new Member(params)
        controller.show(member)

        then: "A model is populated containing the domain instance"
        model.memberInstance == member
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def member = new Member(params)
        controller.edit(member)

        then: "A model is populated containing the domain instance"
        model.memberInstance == member
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/member/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def member = new Member()
        member.validate()
        controller.update(member)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.memberInstance == member

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        member = new Member(params).save(flush: true)
        controller.update(member)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/member/show/$member.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/member/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def member = new Member(params).save(flush: true)

        then: "It exists"
        Member.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(member)

        then: "The instance is deleted"
        Member.count() == 0
        response.redirectedUrl == '/member/index'
        flash.message != null
    }

    void "Test that the saveSimpleAccount action renders the correct view"() {

        when: "The saveSimpleAccount action is executed with a valid instance"
        controller.memberService = memberServiceMock
        populateValidParams(params)
        controller.saveSimpleAccount()

        then: "The saveSimpleAccount view is rendered"
        view == '/member/saveSimpleAccount'

        when: "The saveSimpleAccount action is executed with an invalid instance"
        controller.memberService = memberServiceMockErr
        controller.saveSimpleAccount()

        then: "The createSimpleAccount view is rendered"
        view == '/member/createSimpleAccount'
    }

    void "Test that the showSimpleAccount action renders the correct view"() {
        when: "The showSimpleAccount action is called"
        controller.springSecurityService = springSecurityService
        controller.showSimpleAccount()

        then: "The showSimpleAccount view is rendered"
        view == '/member/showSimpleAccount'
    }

    void "Test that the deleteSimpleAccount action renders the correct view and log out the user"() {
        when: "The deleteSimpleAccount action is called"
        controller.springSecurityService = springSecurityService
        controller.memberService = memberServiceMock
        controller.deleteSimpleAccount()

        then: "The deleteSimpleAccount view is rendered"
        view == '/member/deleteSimpleAccount'
        SecurityContextHolder.getContext().getAuthentication() == null

    }

    void "Test that the editSimpleAccount action renders the correct view"() {
        when: "The editSimpleAccount action is called"
        controller.springSecurityService = springSecurityService
        controller.editSimpleAccount()

        then: "The editSimpleAccount view is rendered"
        view == '/member/editSimpleAccount'
    }

    void "Test that the updateSimpleAccount action with valid parameters redirects to the correct action"() {
        given: "A member"
        def mb = Mock(Member)
        controller.memberService = memberServiceMock

        when: "The updateSimpleAccount action is done"
        controller.updateSimpleAccount(mb, mb.password)

        then: "The showSimpleAccount action is called"
        response.redirectedUrl == '/member/showSimpleAccount'
    }

    void "Test that the updateSimpleAccount action with invalid parameters renders the correct view"() {
        given: "A member"
        def mb = new Member(params)
        controller.memberService = memberServiceMock

        when: "The updateSimpleAccount action is called with invalid parameters"
        mb.password = "mdp"
        controller.updateSimpleAccount(mb, "notmdp")

        then: "The editSimpleAccount view is rendered"
        view == 'editSimpleAccount'
    }
}
