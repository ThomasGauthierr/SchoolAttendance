package fr.mbds.firstgrails

import fr.mbds.firstgrails.exceptions.UserNotFoundException
import grails.converters.JSON
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

class ApiController {

    static responseFormats = ['json']

    def userService
    def userCustomService

    def courseService
    def courseCustomService

    def sessionService
    def sessionCustomService

    def studentService

    def index() {
        switch (request.getMethod()) {
            case "POST":
                render text: "POST"
                println request.getHeader(name: 'Allow')
                break
            case "GET" :
                render text: "GET"
                break
            default:
                render text: "DEFAULT"
                break
        }
    }

    def user(Long id, String name, Integer max) {
        println id
        switch (request.getMethod()) {
            case "GET" :
                if(!max) {
                    max = 10
                }

                if(id) {
                    def user = userService.get(id)
                    if (user)
                        render user as JSON
                    else
                        response.status = 404
                        render ([error: "No user found for the provided parameters"] as JSON)
                }

                if(name) {
                    def user = userCustomService.findByName(name)
                    if(user)
                        render user as JSON
                    else
                        response.status = 404
                        render ([error: "No user found for the provided parameters"] as JSON)
                }

                if(id || name) {
                    response.status = 400
                    render ([error: "Bad or no parameters provided"] as JSON)
                }

                if(!id || !name) {
                    response.status = 400
                    render ([error: "Bad or no parameters provided"] as JSON)
                }

                render userCustomService.list(max: max) as JSON
            break

            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)
                def createdUser = new User(bodyJson)
                createdUser.messageReceived = []
                createdUser.messageSent = []
                if(createdUser.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdUser as JSON
                } else {
                    response.status = 400
                }
                break

            case "PUT":
                if(!params.id) {
                    response.status = 404
                    render ([error: "User to update not found"] as JSON)
                }
                //def bodyJson = JSON.parse(request.reader.text)
                def user = userCustomService.get(params.id)

                def map = new JsonSlurper().parseText(request.reader.text)
                //println map
                if(user) {
                    map.each { key, value ->
                        user."${key}" = value
                    }

                    if(userService.save(user)) {
                        render user as JSON
                    }
                }
            break

            case "DELETE":
                try {
                    userCustomService.delete(params.id)
                    render (['message': 'The user has been deleted successfully.'] as JSON)
                } catch (UserNotFoundException unf) {
                    response.status = 400
                    render (['error': 'No user found for the provided id.'] as JSON)
                }
            break

            default:
                response.status = 405
            break
        }
    }

    def users(Integer nb) {
        switch (request.getMethod()) {
            case "GET":
                if (!nb) {
                    nb = 10
                }

                render userCustomService.list(max: nb) as JSON
            break

            case 'POST':
                def bodyJson =  JSON.parse(request.reader.text)
                bodyJson.each {
                    def createdUser = new User(it)
                    createdUser.messageReceived = []
                    createdUser.messageSent = []
                    if(!createdUser.save(flush: true)) {
                        response.status = 400
                        render ([error: "A problem happened when adding the user collection"] as JSON)
                    }
                }

                response.status = 201
                response.contentType = 'text/json'
                render ([error: "Users have been created successfully"] as JSON)
            break
        }
    }

    def course(Integer teacherId) {
        switch(request.getMethod()) {
            case "GET":
                def courses = new ArrayList()

                if (teacherId == null) {
                    courses = courseService.list()

                    if (courses.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No course found.'] as JSON)
                        break
                    }

                } else {
                    courses = courseCustomService.getCoursesByTeacherId(teacherId)
                    if (courses.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No course found for the specified id.'] as JSON)
                        break
                    }
                }
                response.status = 201
                response.contentType = 'text/json'
                render courses as JSON

                break
            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)

                def name = bodyJson['name']
                def descr = bodyJson['description']
                def teacherUsername = bodyJson['teacher']

                def teacher = userCustomService.getTeacherByUsername(teacherUsername)

                def createdCourse = new Course(
                        name: name,
                        description: descr,
                        teacher: teacher
                )
                if(createdCourse.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdCourse as JSON
                } else {
                    response.status = 400
                    response.contentType = 'text/json'
                    render (['error': 'Your JSON body parameters are incorrect.'] as JSON) as JSON
                }
                break
        }
    }

    def session(Integer courseId) {
        switch(request.getMethod()) {
            case "GET":
                def sessions = new ArrayList()

                if (courseId == null) {
                    sessions = sessionService.list()

                    if (sessions.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No session found.'] as JSON)
                        break
                    }
                } else {
                    sessions = sessionCustomService.getSesionsByCourseId(courseId).each() { elem -> sessions.push(elem)}
                    if (sessions.size() == 0) {
                        response.status = 204
                        response.contentType = 'text/json'
                        render (['error': 'No session found for the specified id.'] as JSON)
                        break
                    }
                }
                response.status = 201
                response.contentType = 'text/json'
                render (['error': 'No session found.'] as JSON)

                break
            case "POST":
                def bodyJson =  JSON.parse(request.reader.text)

                def startDate = bodyJson['startDate']
                def endDate = bodyJson['endDate']
                def course = bodyJson['course']

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm")

                def createdSession = new Session(
                        startDate: dateFormat.parse(startDate),
                        endDate: dateFormat.parse(endDate),
                        course: course
                )

                if(createdSession.save(flush: true)) {
                    response.status = 201
                    response.contentType = 'text/json'
                    render createdSession as JSON
                } else {
                    response.status = 400
                    response.contentType = 'text/json'
                    render (['error': 'Your JSON body parameters are incorrect.'] as JSON) as JSON
                }
                break
        }
    }

    def student() {
        switch(request.getMethod()) {
            case "GET":
                def students = studentService.list()

                if (students.size() == 0) {
                    response.status = 204
                    response.contentType = 'text/json'
                    render (['error': 'No student found.'] as JSON)
                    break
                } else {
                    response.status = 201
                    response.contentType = 'text/json'
                    render students as JSON
                }

                break
        }
    }
}
