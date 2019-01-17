package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class CourseCustomService {
    def userService

    def getCoursesByTeacherId(Integer teacherId) {
        User teacherFound = userService.get(teacherId)

        def query = Course.where {
            teacher == teacherFound
        }

        return query.list()
    }
}
