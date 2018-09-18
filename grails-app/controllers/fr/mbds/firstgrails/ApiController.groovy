package fr.mbds.firstgrails

import grails.converters.JSON

class ApiController {
     def userService

    def index() {
        switch (request.getMethod()) {
            case "POST":
                render text: "POST"
                println request.getHeader(name: "Allow")
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

}
