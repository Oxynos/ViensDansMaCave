package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    CellarService cellarService

    @Secured('permitAll')
    def index() {
        def wineRanking = cellarService.wineRanking()
        render(view:"index",model: [wines : wineRanking])
    }
}
