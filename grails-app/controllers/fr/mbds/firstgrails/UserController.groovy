package fr.mbds.firstgrails

import grails.util.Holders
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class UserController {

    def springSecurityService = Holders.applicationContext.springSecurityService

    UserService userService
    UserRoleService userRoleService

    UploadUserProfileImageService uploadUserProfileImageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def editImage(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {

        if (user == null) {
            notFound()
            return
        }

        // Upload the image provided in the input and update the "profileImageName" field in the user to be saved.
        // "profileImageName" contains the name of the image that has just been uploaded.
        String profileImageFilename = uploadUserProfileImageService.uploadProfileImage(params.profileImageFile)
        user.profileImageName = profileImageFilename

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def updateProfileImage() {
        println params.profileImage.getClass()
        render contentType: "text/json", text: '{"name":"amine"}'
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)
//        def user = userService.get(id)
//
//        user.isDeleted = true
//        user.save(flush: true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def save2() {
        def user = new User()
        user.username = params.username
        user.password = params.password
        user.profileImageName = uploadUserProfileImageService.uploadProfileImage(params.profileImage)

        def gamingRole = Role.findByAuthority('ROLE_USER')

        try {
            userService.save(user)
            UserRole.create(user, gamingRole, true)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                render contentType: "text/json", text: '{"name": "' + user.id + '"}'
            }
            '*' { render contentType: "text/json", text: '{"name": "' + user.id + '"}' }
        }
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def display() {
        def user = User.get(springSecurityService.currentUser.id)

        redirect action: "show", params:[id: user.id]
    }
}
