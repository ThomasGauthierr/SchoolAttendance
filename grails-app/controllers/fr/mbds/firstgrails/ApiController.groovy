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
                    render userService.get(id) as JSON
                } else {
                    render userService.list() as JSON
                }
                break

            case "POS":

                def user = new User();
                user.username = params.user.username;
                user.password = params.user.password

                userService.save(user)

        }
    }

    def messages(Long id) {
//        println request.getMethod()
//        println request.getHeader(name: 'Allow')
        switch (request.getMethod()) {
            case ("GET") :
                switch (request.getHeader(name: 'Allow')) {
                    case "text/json":
                        if (id) {
                            def message = messageService.get(id: id)
                            if (message == null) {
                                render(status: 400, "No message with id $id")
                            } else {
                                render message as JSON
                            }
                        } else {
                            render messageService.list() as JSON
                        }
                        break
                    default:
                        response.status = 400
                        break
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
