package fr.mbds.firstgrails

class MainController {

    def springSecurityService

    def index() {
        def user = User.get(springSecurityService.currentUser.id)

        for(def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if(authority.getAuthority() == 'ROLE_ADMIN') {
                render (view: '/index-admin', model: [user: user])
                return
            }
        }
        render (view: '/index-player', model: [user: user])
    }
}