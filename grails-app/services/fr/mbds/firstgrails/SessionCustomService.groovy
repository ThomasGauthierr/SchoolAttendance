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

    def checkStudent(String data) {
        //ToDo : fix NullPointerException on studentCustomService when called by CardReaderSystem
//        Student currentStudent = studentCustomService.getStudentByNfcData(data)
//
//        if (currentStudent != null) {
//            def currentSession = getCurrentSession()
//
//
//            currentStudent.addToParticipations(new Participation(
//                    student: currentStudent,
//                    session: currentSession
//            )).save()
//        }
    }
}
