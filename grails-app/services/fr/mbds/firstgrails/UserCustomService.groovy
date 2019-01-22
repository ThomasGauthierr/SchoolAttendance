package fr.mbds.firstgrails

import fr.mbds.firstgrails.exceptions.UserNotFoundException
import grails.gorm.transactions.Transactional
import org.springframework.boot.context.config.ResourceNotFoundException

@Transactional
class UserCustomService {

    def userService

    // Returns all non-deleted users
    def list(params) {
        return User.where { isDeleted == false }.list(params)
    }

    // "Delete" by changing its flag isDeleted to true and removes its roles.
    // Throws an exception if not user with the id specified is found
    def delete(Serializable id) throws UserNotFoundException {
        def user = userService.get(id)

        if(!user)
            throw new UserNotFoundException()

        user.isDeleted = true
        user.save(flush: true)
        UserRole.where { user == user }.deleteAll()
    }

    // Return the user with the name passed as argument
    def findByName(String name) {
        return User.findByUsername(name)
    }

    // Returns the user whose ID has been passed as parameter;
    // Returns null if the user has been deleted.
    def get(Serializable id) {
        def user = User.get(id)
        return user ? (user.isDeleted ? null : user) : null
    }

    def userNotFoundException(final ResourceNotFoundException eexception) {
        //logException exception
        render view: 'notFound',  model: [id: params.id, exception: exception]
    }

    // Returns the list (maximum 3 elements) of the last connected users
    // Used in the MainController, function index if the user is an admin.
    def getUsersWithConnection() {
        def query = User.where {
            lastConnection != null
        }

        def listUsers = query.list().sort { it.lastConnection }

        if (listUsers.size() > 3) {
            return listUsers([max:3])
        }

        return listUsers
    }

    def getTeacherByUsername(String teacherUsername) {
        def query = User.where {
            username == teacherUsername
        }

        def result = query.list()

        if (result.size() > 0) {
            return result.first()
        }

        return null
    }
}
