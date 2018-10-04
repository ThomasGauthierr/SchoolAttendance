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

    def matches(long id, String username, Integer max) {

        switch(request.getMethod()) {
            case 'GET' :
                if(!max) {
                    max = 10
                }

                if(id) {
                    def match = matchService.get(id)
                    if(match)
                        render match as JSON
                }

                if(id) {
                    response.status = 400
                    render([error: 'No match found for the provided parameters'] as JSON)
                }

                render matchService.list(max: max) as JSON

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

    def users(long id, String name, Integer max) {
        switch (request.getMethod()) {
            case "GET" :
                if(!max) {
                    max = 10
                }

                if(id) {
                    def user = userService.get(id)
                    if (user)
                        render user as JSON

                }

                if(name) {
                    def user = userCustomService.findByName(name)
                    if(user)
                        render user as JSON
                }

                if(id || name) {
                    response.status = 400
                    render ([error: "No user found for the provided parameters"] as JSON)
                }

                render userCustomService.list(max: max) as JSON
            break

            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdUser = new User(bodyJson)
                if(createdUser.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdUser as JSON
                } else {
                    response.status = 400
                }
            break

            case "PUT":
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

    def messages(Long id, Long user, Boolean read) {
        switch (request.getMethod()) {

            case ("GET") :
                def messages = []

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
                    messageService.list().each {elem -> messages.push(elem)}
                }

                render messages as JSON
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
                def messages = []

                if(params.id)
                    messages.push(messageService.get(params.id))
                else if(params.read) {

                }

                if(messages.size() == 0) {
                    response.status = 400
                    render (['error': 'No message found for the provided parameters.'] as JSON)
                }

                // Deleting all the selected messages.
                for(def message in messages) {
                    messageService.delete(message)
                }
                render (['message': 'The messages has been deleted successfully.'] as JSON)
            break

            default:
                response.status = 405
                break
        }
    }
}
