package fr.mbds.firstgrails

/**
 * Taglib for retrieving the current connected user's information.
 */
class CurrentUserInfoTagLib {

    static namespace = 'curUsr'

    def springSecurityService

    def info = { attrs ->
        def user = springSecurityService.getCurrentUser()
        if(user) {
            if(attrs.id) {
                out << user.id
            }
            if(attrs.imageName) {
                out << user.profileImageName
            }
        }
    }
}
