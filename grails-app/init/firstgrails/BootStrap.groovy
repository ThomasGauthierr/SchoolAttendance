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
                description: "Oracle administration courses to apprehend complex databases structures and principles",
                teacher: teacherOne
        ).save(flush: true, failOnError: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm")
        String strdateStart = "19-01-2019 08-30"
        String strdateEnd = "19-01-2019 23-30"
        Date dateStart = dateFormat.parse(strdateStart)
        Date dateEnd = dateFormat.parse(strdateEnd)

        def session = new  Session(
                startDate: dateStart,
                endDate: dateEnd,
                course: course
        ).save(flush: true, failOnError: true)

        String strdate1 = "23-01-2019 08-30"
        String strdate2 = "23-01-2019 11-00"
        Date date1 = dateFormat.parse(strdate1)
        Date date2 = dateFormat.parse(strdate2)

        def session2 = new  Session(
                startDate: date1,
                endDate: date2,
                course: course
        ).save(flush: true, failOnError: true)

//        session.addToParticipations(new Participation(
//                student: student1,
//                session: session,
//                delay: false
//        )).save(flush: true, failOnError: true)

        UserRole.create(adminUser, adminRole,true)
        UserRole.create(teacherOne, teacherRole, true)


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

        JSON.registerObjectMarshaller(Course) {
            def output = [:]
            output['id'] = it.id
            output['name'] = it.name
            output['description'] = it.description
            output['teacher'] = it.teacher.username
            output['sessions'] = it.sessions.collect {
                session -> [
                        id: session.id,
                        startDate: session.startDate,
                        endDate: session.endDate,
                        participations: session.participations.collect {
                            participation -> [
                                    id: participation.id,
                                    delay: participation.delay,
                                    student: participation.student
                            ]
                        }
                ]
            }

            output
        }
    }


}
