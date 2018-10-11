package fr.mbds.firstgrails

import grails.util.Holders
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MessageController {

    def springSecurityService = Holders.applicationContext.springSecurityService

    MessageService messageService
    MessageCustomService messageCustomService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond messageService.list(params), model:[messageCount: messageService.count()]
    }

    def show(Long id) {
        def user = springSecurityService.currentUser

        // If the user is not an admin, it can only see the message for which is the author or target.
        if (!user.authorities.any { it.authority == "ROLE_ADMIN" }) {
            // If he tries to access a message he's not related to, he's redirected to the display view.
            if (!messageCustomService.checkAccess(user, id)) {
                redirect action: "display"
                return
            }
        }

        // Checks if the user reading the mesage is the target. If it's the case, sets the message as "read".
        messageCustomService.checkRead(springSecurityService.currentUser.id, id)

        respond messageService.get(id)
    }

    def create() {
        respond new Message(params)
    }

    def save(Message message) {
        if (message == null) {
            notFound()
            return
        }

        // The author has to be different from the target.
        if (message.author == message.target) {
            flash.message = "message.create.error.same"
            redirect action: "create"
            return
        }

        // The message content cannot be null.
        if (message.content == null) {
            flash.message = "message.create.error.content"
            redirect action: "create"
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect message
            }
            '*' { respond message, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond messageService.get(id)
    }

    def update(Message message) {
        if (message == null) {
            notFound()
            return
        }

        // The author has to be different from the target.
        if (message.author == message.target) {
            flash.message = "message.create.error.same"
            redirect action: "edit", id: message.id
            return
        }

        // The message content cannot be null.
        if (message.content == null) {
            flash.message = "message.create.error.content"
            redirect action: "edit", id: message.id
            return
        }

        try {
            messageService.save(message)
        } catch (ValidationException e) {
            respond message.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'message.label', default: 'Message'), message.id])
                redirect message
            }
            '*'{ respond message, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        messageService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'message.label', default: 'Message'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'message.label', default: 'Message'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    // Displays a list of all the messages where the current user is the target or the author.
    def display() {
        def user = User.get(springSecurityService.currentUser.id)
        def results = messageCustomService.getAllUserMessages(user)

        respond results
    }
}
