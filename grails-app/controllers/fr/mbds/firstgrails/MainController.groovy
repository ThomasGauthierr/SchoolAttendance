package fr.mbds.firstgrails

import grails.util.Holders

import javax.servlet.http.Cookie
import java.text.DateFormat
import java.text.SimpleDateFormat


class MainController {

    def springSecurityService = Holders.applicationContext.springSecurityService
    def userService
    def matchService

    def index() {
        def user = User.get(springSecurityService.currentUser.id)

        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                params.max = Math.min(10 ?: 10, 100)
                respond userService.list(params), view: '/index-admin', model:[user: user, userCount: userService.count(), matches: matchService.list(params)]
                return
            }
        }


        render (view: '/index-player', model: [user: user])
    }

    //ToDo : Only POST
    def connection() {
        def user = User.get(springSecurityService.currentUser.id)

        user.previousConnection = user.lastConnection
        user.lastConnection = new Date()
        user.save(flush: true)

        redirect(controllerName:"main", method: "POST")
    }
}