package fr.mbds.firstgrails

import grails.converters.JSON

class ApiController {

    def userService
    def messageService
    def matchService

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

    def users(long id) {
        switch (request.getMethod()) {
            case "GET" :
                if(id != 0) {
                    def user = userService.get(id)
                    if(user)
                        render user as JSON
                    else
                        response.status = 400
                    render 
                } else {
                    render userService.list() as JSON
                }
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

            default:
                response.status = 405
            break;
        }
    }

    // Pas la peine de mettre des switches pour "allow" dans une requette GET car dans tout les cas impossible de transmetre ni du json ni de l'xml car body vide.
    def messages(Long id) {
        switch (request.getMethod()) {
            case ("GET") :
                        if (id) {
                            def message = messageService.get(id)
                            if (message == null) {
                                render(status: 400, "No message with id $id")
                            } else {
                                render message as JSON
                            }
                        } else {
                            render messageService.list() as JSON
                        }
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
