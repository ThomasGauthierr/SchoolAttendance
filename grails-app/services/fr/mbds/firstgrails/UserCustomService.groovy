package fr.mbds.firstgrails

import fr.mbds.firstgrails.exceptions.UserNotFoundException
import grails.gorm.transactions.Transactional
import org.springframework.boot.context.config.ResourceNotFoundException

@Transactional
class UserCustomService {

    def userService

    def list(params) {
        return User.where { isDeleted == false }.list(params)
    }

    def delete(Serializable id) throws UserNotFoundException {
        def user = userService.get(id)

        if(!user)
            throw new UserNotFoundException()

        user.isDeleted = true
        user.save(flush: true)
        UserRole.where { user == user }.deleteAll()
    }

    def findByName(String name) {
        return User.findByUsername(name)
    }

    def get(Serializable id) {
        def user = User.get(id)
        return user ? (user.isDeleted ? null : user) : null
    }

    def userNotFoundException(final ResourceNotFoundException eexception) {
        //logException exception
        render view: 'notFound',  model: [id: params.id, exception: exception]
    }
}
