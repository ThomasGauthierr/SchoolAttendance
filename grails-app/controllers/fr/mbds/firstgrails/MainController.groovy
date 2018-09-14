package fr.mbds.firstgrails

class MainController {

    def springSecurityService
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
}