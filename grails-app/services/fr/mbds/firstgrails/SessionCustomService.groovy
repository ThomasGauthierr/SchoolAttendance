package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

import java.text.DateFormat
import java.text.SimpleDateFormat

@Transactional
class SessionCustomService {

    def courseService
    def studentCustomService

    def getSessionsByCourseId(Integer courseId) {
        def foundCourse = courseService.get(courseId)

        def query = Session.where {
            course == foundCourse
        }

        return query.list()
    }

    def getCurrentSession() {

        def result = Session.withCriteria {
            def now = new Date()
            le('startDate', now)
            ge('endDate', now)
        }

        if (result.size > 0) {
            return result.first()
        } else {
            return null
        }
    }

    def checkSessionForDate(Date dateBeginning, Date dateEnd) {
        def result = Session.withCriteria {
            def now = new Date()
            between('startDate', dateBeginning, dateEnd)
            between('endDate', dateBeginning, dateEnd)
        }

        return result.size > 0
    }

    def checkStudent(String data) {
        Student currentStudent = studentCustomService.getStudentByNfcData(data)

        if (currentStudent != null) {
            Session currentSession = getCurrentSession()

            if (currentSession != null) {

                def now = new Date()

                def diff = now.getTime() - currentSession.startDate.getTime()
                long diffMinutes = diff / (60 * 1000) % 60

                print (diffMinutes)

                currentStudent.addToParticipations(new Participation(
                        student: currentStudent,
                        session: currentSession
                )).save()
            }

        }
    }

    def getDelayedStudents(Integer sessionId) {
        def students = new ArrayList()

        def participationsDelayed = Participation.where {
            delay == true && session.id == sessionId
        }.list()

        participationsDelayed.each() { e ->
            students.add(e.student)
        }

        return students
    }

    def getPresentStudents(Integer sessionId) {
        def students = new ArrayList()

        def participationsNotDelayed = Participation.where {
            delay == false && session.id == sessionId
        }.list()

        participationsNotDelayed.each() { e ->
            students.add(e.student)
        }

        return students
    }

    def getMissingStudents(Integer sessionId) {
        def students = Student.list()

        def delayedStudents = getDelayedStudents(sessionId)
        def presentStudents = getPresentStudents(sessionId)

        for (int i = 0; i < delayedStudents.size(); i++) {
            students.remove(delayedStudents.get(i))
        }

        for (int i = 0; i < presentStudents.size(); i++) {
            students.remove(presentStudents.get(i))
        }

        return students
    }
}
