package viensdansmacave

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    CellarService cellarService

    @Secured('permitAll')
    def index() {
        def wineRanking = cellarService.wineRanking()
        def cellarRanking = cellarService.getCellarRanking()
        render(view:"index",model: [wines : wineRanking, cellars: cellarRanking])
    }
}
