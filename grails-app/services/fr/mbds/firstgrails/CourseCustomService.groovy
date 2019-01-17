package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class CourseCustomService {

    def getCoursesByTeacherId(Integer teacherId) {
        User teacher = User.get(teacherId)

        def query = Courses.where {
            user == teacher
        }

        return query.list()
    }
}
