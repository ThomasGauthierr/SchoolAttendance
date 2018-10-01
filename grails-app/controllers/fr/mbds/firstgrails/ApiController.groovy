package fr.mbds.firstgrails

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

    def match(long id, String username, Integer max) {

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

                render userService.list(max: max) as JSON

            break

        /**
         * Exemple: Try posting this object to the route "api/users"
         *
         * {
         * 	"user":
         * 	{
         * 		"username": "m6",
         * 		"password": "123"* 	}
         * }
         *
         */
            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdUser = new User(bodyJson.user)
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

            default:
                response.status = 405
            break
        }
    }

    // Pas la peine de mettre des switches pour "allow" dans une requette GET car dans tout les cas impossible de transmetre ni du json ni de l'xml car body vide.
    def message(Long id, String username) {
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
                switch (request.getHeader(name: 'Allow'))  {
                    case "text/json" :
                        if (!userService.get(params.author.id) && !userService.get(params.target.id)) {
                            render(status: 400, text: "Author and target do not exist")
                            return
                        } else if (!userService.get(params.author.id)) {
                            render(status: 400, text: "Author does not exist")
                            return
                        } else if (!userService.get(params.target.id)) {
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
                        break
                    default:
                        response.status = 405
                        break
                    }
                break
            default:
                response.status = 405
                break
        }
    }
}
