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

    // Pas la peine de mettre des switches pour "allow" dans une requette GET car dans tout les cas impossible de transmetre ni du json ni de l'xml car body vide.
    def messages(Long id, String username) {
        switch (request.getMethod()) {
            case ("GET") :
                if (id) {
                    def message = messageService.get(id)
                    if (message == null) {
                        render(status: 400, "No message with id $id")
                    } else {
                        render message as JSON
                    }
                }

                if(username) {
                    def messages = messageCustomService.findMessagesByUsername(username)
                    render messages as JSON
                }

                if(id || username) {
                    response.status = 400
                    render ([error: "No user found for the provided parameters"] as JSON)
                }

                render messageService.list() as JSON

            break

            case "POST":
/*                        if (!userService.get(params.author.id) && !userService.get(params.target.id)) {
                            render(status: 400, text: "Author and target do not exist")
                            return
                        } else if (!userService.get(params.author.id)) {
                            render(status: 400, text: "Author does not exist")
                            return
                        } else if (!userService.get(params.target.id)) {Ò
                            render(status: 400, text: "Target does not exist")
                            return
                        } else {
                            def message = new Message(params)
                            if (message.save(flush: true)) {
                                response.status = 201
                            } else {
                                response.status = 400
                            }
                        }
                        break */
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
                def message = messageService.get(params.id);

                if(!message) {
                    response.status = 400
                    render (['error': 'No message found for the provided id.'] as JSON)
                }

                messageService.delete(params.id)
                render (['message': 'The message has been deleted successfully.'] as JSON)
            break

            default:
                response.status = 405
                break
        }
    }
}
