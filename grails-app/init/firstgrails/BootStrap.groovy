package firstgrails

import fr.mbds.firstgrails.*

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def gamingRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'admin', password: 'password').save(flush: true)
        def playerUser = new User(username: 'player', password: 'password').save(flush: true)
        def playerTwoUser = new User(username: 'playerTwo', password: 'password').save(flush: true)

        UserRole.create(adminUser, adminRole,true)
        UserRole.create(playerUser, gamingRole, true)
        UserRole.create(playerTwoUser, gamingRole, true)

        //new Match(winner: playerUser, looser: playerTwoUser, winnerScore: 100, looserScore: 1).save(flush: true, failOnError: true)

        //new Message(author: playerUser, target: playerTwoUser, content: "hello").save(flush: true, failOnError: true)
        //new Message(author: playerTwoUser, target: playerUser, content: "hi").save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}