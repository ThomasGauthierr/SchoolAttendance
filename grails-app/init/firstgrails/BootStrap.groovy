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


//        def user1 = new User(username: 'user1').save(flush: true, failOnError: true)
//        def user2 = new User(username: 'user2').save(flush: true, failOnError: true)
//
//        new Match(winner: user1, looser: user2, winnerScore: 100, looserScore: 1).save(flush: true, failOnError: true)
//
//        new Message(author: user1, target: user2, content: "hello").save(flush: true, failOnError: true)
//        new Message(author: user2, target: user1, content: "hi").save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
