package fr.mbds.firstgrails

import grails.util.Holders

import javax.servlet.http.Cookie
import java.text.DateFormat
import java.text.SimpleDateFormat


class MainController {

    def springSecurityService = Holders.applicationContext.springSecurityService
    def userService

    def index() {
        def user = User.get(springSecurityService.currentUser.id)

        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                params.max = Math.min(10 ?: 10, 100)
                respond userService.list(params), view: '/index-admin', model:[user: user, userCount: userService.count()]
                return
            }
        }


        render (view: '/index-player', model: [user: user])
    }

    def connection() {
        def user = User.get(springSecurityService.currentUser.id)

        def lastConnection = user.lastConnection.toString().replace(" ","_")
        def lastConnectionCookie = new Cookie('lastConnection', lastConnection)
        response.addCookie(lastConnectionCookie)

        Date date = new Date()
        user.lastConnection = date
        user.save(flush: true)

        redirect(controllerName:"main", method: "POST")
    }
}