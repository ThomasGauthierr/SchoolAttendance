package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class SessionCustomService {

    def courseService

    def getSesionsByCourseId(Integer courseId) {
        def foundCourse = courseService.get(courseId)

        def query = Session.where {
            course == foundCourse
        }

        return query.list()
    }
}
