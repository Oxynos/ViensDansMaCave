package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    @Secured('permitAll')
    def index() { }
}
