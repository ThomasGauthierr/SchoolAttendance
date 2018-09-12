package firstgrails

import fr.mbds.firstgrails.*

class BootStrap {

    def init = { servletContext ->

        def user1 = new User(username: 'user1').save(flush: true, failOnError: true)
        def user2 = new User(username: 'user2').save(flush: true, failOnError: true)

        new Match(winner: user1, looser: user2, winnerScore: 100, looserScore: 1).save(flush: true, failOnError: true)

        new Message(author: user1, target: user2, content: "hello").save(flush: true, failOnError: true)
        new Message(author: user2, target: user1, content: "hi").save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
