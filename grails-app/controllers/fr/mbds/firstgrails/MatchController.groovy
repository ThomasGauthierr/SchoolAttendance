package fr.mbds.firstgrails

import grails.util.Holders
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MatchController {
    def userService

    def springSecurityService = Holders.applicationContext.springSecurityService

    MatchService matchService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond matchService.list(params), model:[matchCount: matchService.count()]
    }

    def show(Long id) {
        respond matchService.get(id)
    }

    def create() {
        respond new Match(params)
    }

    def save(Match match) {
        if (match == null) {
            notFound()
            return
        }

        if (match.winner == null && match.looser == null) {
            flash.message = "match.create.error.noUser"
            redirect action: "create"
            return
        }

        if (match.winnerScore != null && match.winner == null ||
            match.looserScore != null && match.looser == null) {

            flash.message = "match.create.error.score-user"
        }

        if (match.winnerScore == null && match.winner != null ||
            match.looserScore == null && match.looser != null) {

            flash.message2 = "match.create.error.user-score"
        }

        if (flash.message || flash.message2) {
            redirect action: "create"
            return
        }

        try {
            matchService.save(match)
        } catch (ValidationException e) {
            println "ERROR"
            respond match.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*' { respond match, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond matchService.get(id)
    }

    def update(Match match) {
        if (match == null) {
            notFound()
            return
        }

        try {
            matchService.save(match)
        } catch (ValidationException e) {
            respond match.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'match.label', default: 'Match'), match.id])
                redirect match
            }
            '*'{ respond match, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        matchService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'match.label', default: 'Match'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'match.label', default: 'Match'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    //ToDo : Separate win and lost matches
    def display() {
        def user = User.get(springSecurityService.currentUser.id)

        def query = Match.where {
            winner == user || looser == user
        }
        def results = query.list()

        respond results
    }
}
