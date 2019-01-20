package firstgrails

import fr.mbds.firstgrails.*
import grails.converters.JSON

import java.text.DateFormat
import java.text.SimpleDateFormat

class BootStrap {

    def sessionCustomService

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
                nfcUid: "04 51 81 6A 34 5E 80 ",
                firstName: "Thomas",
                lastName: "Gauthier",
                address: "Vesoul",
                mail: "thomasg70360@gmail.com"
        ).save(flush: true, failOnError: true)

        def student2 = new Student(
                student_number: 20908325,
                nfcUid: "04 91 FC E2 5E 5C 80 ",
                firstName: "Amine",
                lastName: "Ait Errami",
                address: "Nice",
                mail: "aiterramimine@gmail.com"
        ).save(flush: true, failOnError: true)


        def course = new Course(
                name: "DBA",
                teacher: teacherOne
        ).save(flush: true, failOnError: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy mm-HH")
        String strdateStart = "19-01-2019 08-30"
        String strdateEnd = "20-01-2019 23-30"
        Date dateStart = dateFormat.parse(strdateStart)
        Date dateEnd = dateFormat.parse(strdateEnd)

        def session = new  Session(
                startDate: dateStart,
                endDate: dateEnd,
                course: course
        ).save(flush: true, failOnError: true)

        String strdate1 = "23-05-2019 08-30"
        String strdate2 = "23-05-2019 11-00"
        Date date1 = dateFormat.parse(strdate1)
        Date date2 = dateFormat.parse(strdate2)

        def session2 = new  Session(
                startDate: date1,
                endDate: date2,
                course: course
        ).save(flush: true, failOnError: true)

        sessionCustomService.checkStudent("04 51 81 6A 34 5E 80 ")

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
