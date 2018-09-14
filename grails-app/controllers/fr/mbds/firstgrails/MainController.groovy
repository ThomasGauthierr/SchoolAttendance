package fr.mbds.firstgrails

import grails.plugin.springsecurity.annotation.Secured

class MainController {

    def springSecurityService

    def index() {
        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                render (view: '/index-admin', user: User.get(params.id))
                return
            }
        }
        render (view: '/index-player', user: User.get(params.id))
    }
}