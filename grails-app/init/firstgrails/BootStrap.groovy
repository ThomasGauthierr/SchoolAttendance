package firstgrails

import fr.mbds.firstgrails.*
import grails.converters.JSON

import java.text.DateFormat
import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->

        customizeJSONMarshaller()

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def teacherRole = new Role(authority: 'ROLE_TEACHER').save(flush: true, failOnError: true)

//        def gamingRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)

        def adminUser = new User(
            username: 'admin',
            password: 'password',
            person: new Person(
                firstName: "Gabriel",
                lastName: "Mopolo",
                address: "06600 Antibes",
                mail: "gabriel.mopolo@unice.fr"
            )
        ).save(flush: true, failOnError: true)
        def teacherOne = new User(
            username: 'serge',
            password: 'password',
            person: new Person(
                firstName: "Serge",
                lastName: "Miranda",
                address: "06100 Nice",
                mail: "serge.miranad@unice.fr"
            )
        ).save(flush: true, failOnError: true)

//        def playerUser = new User(username: 'player', password: 'password').save(flush: true, failOnError: true)
//        def playerTwoUser = new User(username: 'playerTwo', password: 'password').save(flush: true, failOnError: true)
//        def bannedUser = new User(username: 'ban', password: 'password', isDeleted: 'true').save(flush: true, failOnError: true)

        UserRole.create(adminUser, adminRole,true)
        UserRole.create(teacherOne, teacherRole, true)

//        UserRole.create(playerUser, gamingRole, true)
//        UserRole.create(playerTwoUser, gamingRole, true)

//        new Match(winner: playerUser, looser: playerTwoUser, winnerScore: 100, looserScore: 1).save(flush: true, failOnError: true)
//        new Match(winner: playerTwoUser, looser: playerUser, winnerScore: 50, looserScore: 45).save(flush: true, failOnError: true)
//        new Match(winner: bannedUser, looser: playerUser, winnerScore: 150, looserScore: 100).save(flush: true, failOnError: true)

//        def message1 = new Message(author: playerUser, target: playerTwoUser, content: "hello").save(flush: true, failOnError: true)
//        def message2 = new Message(author: playerTwoUser, target: playerUser, content: "hi", read: true).save(flush: true, failOnError: true)

    }
    def destroy = {
    }

    /**
     * Customize JSON Object marshaller
     */
    def customizeJSONMarshaller() {

        /* Custom JSON Object marshaller for the 'User' domain.*/
        JSON.registerObjectMarshaller(User) {
            def output = [:]
            output['id'] = it.id
            output['username'] = it.username
            output['messages'] = it.messageReceived.plus(it.messageSent)
            output['dateCreated'] = it.dateCreated
            output['lastConnection'] = it.lastConnection

            output
        }

        JSON.registerObjectMarshaller(Message) {
            def output = [:]
            output['id'] = it.id
            output['author'] = it.author.username
            output['target'] = it.target.username
            output['content'] = it.content
            output['read'] = it.read
            output['dateCreated'] = it.dateCreated

            output
        }

        JSON.registerObjectMarshaller(Match) {
            def output = [:]
            output['id'] = it.id
            output['winner'] = it.winner.username
            output['looser'] = it.looser.username
            output['winnerScore'] = it.winnerScore
            output['looserScore'] = it.looserScore

            output
        }
    }


}
