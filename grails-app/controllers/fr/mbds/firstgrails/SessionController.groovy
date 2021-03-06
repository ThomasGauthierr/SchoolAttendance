package fr.mbds.firstgrails

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.NOT_FOUND

class SessionController {

    SessionService sessionService
    SessionCustomService sessionCustomService

    def index() {
        respond sessionService.list(params), model:[sessionCount: sessionService.count()]
    }

    def create() {
        respond new Session(params)
    }

    def save(Session session) {

        if (session == null) {
            notFound()
            return
        }

        if (session.startDate < session.endDate) {
            def tmp
            tmp = session.startDate
            session.startDate = session.endDate
            session.endDate = tmp
        }


        try {

            if (sessionCustomService.checkSessionForDate(session.startDate, session.endDate)) {
                //ToDO : error when creating 2 sessions at the same time
                print "\n\n2 sessions \n\n"
            }

            sessionService.save(session)
        } catch (ValidationException e) {
            respond session.errors, view:'create'
            return
        }

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'session.label', default: 'Role'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def show(Long id) {
        respond sessionService.get(id)
    }

    def delete(Long id) {
        respond sessionService.delete(id)
    }

}
