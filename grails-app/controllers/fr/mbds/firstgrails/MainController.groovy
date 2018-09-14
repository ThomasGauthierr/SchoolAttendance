package fr.mbds.firstgrails

import grails.plugin.springsecurity.annotation.Secured

class MainController {

    def springSecurityService

    def index() {
        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                render "You are an admin"
                return
            }
        }
        render "You are not an admin"
    }
}