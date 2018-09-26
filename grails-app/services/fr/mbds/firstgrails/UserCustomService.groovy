package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class UserCustomService {
    def userService

    def list(params) {
        return User.where { isDeleted == false }.list(params)
    }

    def delete(Serializable id) {
        def user = userService.get(id)
        user.isDeleted = true
        user.save(flush: true)
    }

    def findByName(String name) {
        return User.findByUsername(name)
    }

    def get(Serializable id) {
        def user = User.get(id)
        return user ? (user.isDeleted ? null : user) : null
    }
}
