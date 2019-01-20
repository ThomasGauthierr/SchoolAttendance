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
            firstName: 'Gabriel',
            lastName: 'Mopolo',
            address: '06600 Antibes',
            mail: 'gabriel.mopolo@unice.fr'
        ).save(flush: true, failOnError: true)
        def teacherOne = new User(
            username: 'serge',
            password: 'password',
            firstName: 'Serge',
            lastName: 'Miranda',
            address: '06100 Nice',
            mail: "serge.miranad@unice.fr"
        ).save(flush: true, failOnError: true)

        def student1 = new Student(
                student_number: 21204226,
                nfcTag: 21204226,
                firstName: "Thomas",
                lastName: "Gauthier",
                address: "Vesoul",
                mail: "thomasg70360@gmail.com"
        ).save(flush: true, failOnError: true)

        def student2 = new Student(
                student_number: 20908325,
                nfcTag: 20908325,
                firstName: "Amine",
                lastName: "Ait Errami",
                address: "Nice",
                mail: "aiterramimine@gmail.com"
        ).save(flush: true, failOnError: true)

        def course = new Course(
                name: "DBA",
                teacher: teacherOne
        ).save(flush: true, failOnError: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")
        String strdate = "02-04-2019"
        Date date = dateFormat.parse(strdate)

        def session = new  Session(
                date: date,
                startHours: 8,
                startMins: 0,
                endHours: 9,
                endMins: 0,
                course: course
        ).save(flush: true, failOnError: true)

        strdate = "23-05-2019"
        date = dateFormat.parse(strdate)

        def session2 = new  Session(
                date: date,
                startHours: 10,
                startMins: 0,
                endHours: 12,
                endMins: 30,
                course: course
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
