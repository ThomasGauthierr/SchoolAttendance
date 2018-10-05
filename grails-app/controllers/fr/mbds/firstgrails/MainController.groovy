package fr.mbds.firstgrails

class MainController {

    def springSecurityService
    def userService
    def matchService
    def userCustomService

    def index() {
        def user = User.get(springSecurityService.currentUser.id)

        for (def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if (authority.getAuthority() == 'ROLE_ADMIN') {
                params.max = Math.min(4 ?: 10, 100)
                render view: '/index-admin', model: [user: user, userCount: userService.count(), matches: matchService.list(params), userList: userCustomService.getUsersWithConnection()]
                return
            }
        }

        render(view: '/index-player', model: [user: user])
    }

    def connection() {
        def user = User.get(springSecurityService.currentUser.id)

        if (user.isDeleted) {
            render (view: "/index-ban")
        } else {
            user.previousConnection = user.lastConnection
            user.lastConnection = new Date()
            user.save(flush: true)

            redirect(controllerName:"main", actionName:"index")
        }
    }
}