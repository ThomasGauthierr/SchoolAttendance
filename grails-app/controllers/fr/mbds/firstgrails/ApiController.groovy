package fr.mbds.firstgrails

import fr.mbds.firstgrails.exceptions.UserNotFoundException
import grails.converters.JSON
import groovy.json.JsonSlurper

class ApiController {

    static responseFormats = ['json']

    def userService
    def messageService
    def matchService
    def userCustomService
    def messageCustomService

    def courseService
    def courseCustomService

    def sessionService
    def sessionCustomService

    def studentService

    def index() {
        switch (request.getMethod()) {
            case "POST":
                render text: "POST"
                println request.getHeader(name: 'Allow')
                break
            case "GET" :
                render text: "GET"
                break
            default:
                render text: "DEFAULT"
                break
        }
    }

    def user(Long id, String name, Integer max) {
        println id
        switch (request.getMethod()) {
            case "GET" :
                if(!max) {
                    max = 10
                }

                if(id) {
                    def user = userService.get(id)
                    if (user)
                        render user as JSON
                    else
                        response.status = 404
                        render ([error: "No user found for the provided parameters"] as JSON)
                }

                if(name) {
                    def user = userCustomService.findByName(name)
                    if(user)
                        render user as JSON
                    else
                        response.status = 404
                        render ([error: "No user found for the provided parameters"] as JSON)
                }

                if(id || name) {
                    response.status = 400
                    render ([error: "Bad or no parameters provided"] as JSON)
                }

                if(!id || !name) {
                    response.status = 400
                    render ([error: "Bad or no parameters provided"] as JSON)
                }

                render userCustomService.list(max: max) as JSON
            break

            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdUser = new User(bodyJson)
                createdUser.messageReceived = []
                createdUser.messageSent = []
                if(createdUser.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdUser as JSON
                } else {
                    response.status = 400
                }
                break

            case "PUT":
                if(!params.id) {
                    response.status = 404
                    render ([error: "User to update not found"] as JSON)
                }
                //def bodyJson = JSON.parse(request.reader.text)
                def user = userCustomService.get(params.id)

                def map = new JsonSlurper().parseText(request.reader.text)
                //println map
                if(user) {
                    map.each { key, value ->
                        user."${key}" = value
                    }

                    if(userService.save(user)) {
                        render user as JSON
                    }
                }
            break

            case "DELETE":
                try {
                    userCustomService.delete(params.id)
                    render (['message': 'The user has been deleted successfully.'] as JSON)
                } catch (UserNotFoundException unf) {
                    response.status = 400
                    render (['error': 'No user found for the provided id.'] as JSON)
                }
            break

            default:
                response.status = 405
            break
        }
    }

    def message(Long id, Long user, Boolean read) {
        switch (request.getMethod()) {

            case ("GET") :
                def message

                if(id)
                    message = messageService.get(id)
                    if(message)
                        render message as JSON
                else {
                    response.status = 404
                    render(['error': 'Couldn\'t find the message with the provided id'] as JSON)
                }
            break

            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdMessage = new Message(bodyJson)
                if(createdMessage.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdMessage as JSON
                } else {
                    response.status = 400
                    render (['error': 'Couldn\'t create the message'] as JSON)
                }
            break

            case 'PUT':
                def message = messageService.get(params.id)
                if(!message) {
                    response.status = 404
                    render (['error': 'Couldn\'t find the message with the provided id'] as JSON)
                }
                def map = new JsonSlurper().parseText(request.reader.text)
                if(message) {
                    map.each { key, value ->
                        message."${key}" = value
                    }

                    if(messageService.save(message)) {
                        render message as JSON
                    }
                }
            break

            case "DELETE":
                if(!params.id) {
                    response.status = 404
                    render (['error': 'Couldn\'t find the message with the provided id'] as JSON)
                }
                try {
                    messageService.delete(params.id)
                    render (['message': 'The user has been deleted successfully.'] as JSON)
                } catch (UserNotFoundException unf) {
                    response.status = 400
                    render (['error': 'No user found for the provided id.'] as JSON)
                }
            break

            default:
                response.status = 405
                break
        }
    }

    def match(Long id, String username, Integer max) {

        switch(request.getMethod()) {
            case ("GET") :
                def match

                if(id)
                    match = matchService.get(id)
                if(match)
                    render match as JSON
                else {
                    response.status = 404
                    render(['error': 'Couldn\'t find the match with the provided id'] as JSON)
                }
                break

            case 'POST' :
                def bodyJson = JSON.parse(request.reader.text)
                def createdMatch = new Match(bodyJson)
                if(createdMatch.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdMatch as JSON
                } else {
                    response.status = 400
                }
                break

            case 'PUT':
                def match = matchService.get(params.id)

                def map = new JsonSlurper().parseText(request.reader.text)
                if(match) {
                    map.each { key, value ->
                        match."${key}" = value
                    }

                    if(matchService.save(match)) {
                        render match as JSON
                    }
                } else {
                    response.status = 404
                    render ([error: "No match was found for the provided parameters"] as JSON)
                }
                break

            case "DELETE":
                def match = matchService.get(params.id);

                if(!match) {
                    response.status = 400
                    render (['error': 'No match found for the provided id.'] as JSON)
                }

                matchService.delete(params.id)
                render (['message': 'The match has been deleted successfully.'] as JSON)
                break

            default :
                response.status = 405
                break

        }
    }

    def users(Integer nb) {
        switch (request.getMethod()) {
            case "GET":
                if (!nb) {
                    nb = 10
                }

                render userCustomService.list(max: nb) as JSON
            break

            case 'POST':
                def bodyJson =  JSON.parse(request.reader.text)
                bodyJson.each {
                    def createdUser = new User(it)
                    createdUser.messageReceived = []
                    createdUser.messageSent = []
                    if(!createdUser.save(flush: true)) {
                        response.status = 400
                        render ([error: "A problem happened when adding the user collection"] as JSON)
                    }
                }

                response.status = 201
                response.contentType = 'text/json'
                render ([error: "Users have been created successfully"] as JSON)
            break
        }
    }

    def messages(Integer nb, Long user, Boolean read, Long id) {
        switch (request.getMethod()) {

            case ("GET"):
                def messages = []

                if (!nb)
                    nb = 10

                if(id) {
                    messages.push(messageService.get(id))
                } else {
                    if(user && read != null) {
                        if(read) {
                            messageCustomService.findReadMessageByUserId(user).each {elem -> messages.push(elem)}
                        } else {
                            messageCustomService.findNonReadMessagesByUserId(user).each {elem -> messages.push(elem)}
                        }
                    } else {
                        if(read != null) {
                            if(read) {
                                messageCustomService.findReadMessages().each {elem -> messages.push(elem)}
                            } else {
                                messageCustomService.findNonReadMessages().each {elem -> messages.push(elem)}
                            }
                        }
                        if(user) {
                            messageCustomService.findMessagesByUser(user).each {elem -> messages.push(elem)}
                        }
                    }

                }

                if(!id && !user && read == null) {
                    messageService.list(max: nb).each { elem -> messages.push(elem) }
                }

                render messages as JSON
            break

            case 'POST':
                def bodyJson =  JSON.parse(request.reader.text)
                bodyJson.each {
                    def createdMessage = new Message(it)
                    if(!createdMessage.save(flush: true)) {
                        response.status = 400
                        render ([error: "A problem happened when adding the message collection"] as JSON)
                    }
                }

                response.status = 201
                response.contentType = 'text/json'
                render ([error: "Messages have been created successfully"] as JSON)
            break
        }
    }

    def matches(Integer nb) {
        switch (request.getMethod()) {

            case ("GET"):
                def matches = []

                if (!nb)
                    nb = 10

                matchService.list(max: nb).each { elem -> matches.push(elem) }

                render matches as JSON
            break

            case 'POST':
                def bodyJson =  JSON.parse(request.reader.text)
                bodyJson.each {
                    def createdMatch = new Match(it)
                    if(!createdMatch.save(flush: true)) {
                        response.status = 400
                        render ([error: "A problem happened when adding the match collection"] as JSON)
                    }
                }

                response.status = 201
                response.contentType = 'text/json'
                render ([error: "Match have been created successfully"] as JSON)
                break
        }
    }

    def course(Integer teacherId) {
        switch(request.getMethod()) {
            case "GET":
                def courses = new ArrayList()

                if (teacherId == null) {
                    courses = courseService.list()

                    if (courses.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No course found.'] as JSON)
                        break
                    }

                } else {
                    courses = courseCustomService.getCoursesByTeacherId(teacherId)
                    if (courses.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No course found for the specified id.'] as JSON)
                        break
                    }
                }
                response.status = 201
                response.contentType = 'text/json'
                render courses as JSON

                break
            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdCourse = new Course(bodyJson)
                if(createdCourse.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdCourse as JSON
                } else {
                    response.status = 400
                }
                response.status = 201
                response.contentType = 'text/json'
                render (['error': 'wazzup.'] as JSON) as JSON
                break
        }
    }

    def session(Integer courseId) {
        switch(request.getMethod()) {
            case "GET":
                def sessions = new ArrayList()

                if (courseId == null) {
                    sessions = sessionService.list()

                    if (sessions.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No session found.'] as JSON)
                        break
                    }
                } else {
                    sessions = sessionCustomService.getSesionsByCourseId(courseId).each() { elem -> sessions.push(elem)}
                    if (sessions.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No session found for the specified id.'] as JSON)
                        break
                    }
                }
                response.status = 201
                response.contentType = 'text/json'
                render (['error': 'No session found.'] as JSON)

                break
        }
    }

    def student() {
        switch(request.getMethod()) {
            case "GET":
                def students = studentService.list()

                if (students.size() == 0) {
                    response.status = 204
                    response.contentType = 'text/json'
                    render (['error': 'No student found.'] as JSON)
                    break
                } else {
                    response.status = 201
                    response.contentType = 'text/json'
                    render students as JSON
                }

                break
        }
    }
}
