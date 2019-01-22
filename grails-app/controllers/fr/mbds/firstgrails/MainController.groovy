package fr.mbds.firstgrails

class MainController {

    def springSecurityService
    def userService
    def userCustomService

    // Called after the connection function below.
    // If the user is an admin, display the "index-admin" view, where there are details about last connection and last matches.
    // Else display the "index-user" view
    def index() {
        def user = User.get(springSecurityService.currentUser.id)

        for (def authority : springSecurityService.getPrincipal().getAuthorities()) {
            if (authority.getAuthority() == 'ROLE_ADMIN') {
                params.max = Math.min(4 ?: 10, 100)
                render view: '/index-admin', model: [user: user, userCount: userService.count(), userList: userCustomService.getUsersWithConnection()]
                return
            }
        }

        render(view: '/index-player', model: [user: user])
    }

    // Called after login. Checks if the user is banned, and if not, update its connection informations.
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