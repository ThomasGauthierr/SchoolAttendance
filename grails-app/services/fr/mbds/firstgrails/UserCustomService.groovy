package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class UserCustomService {
    def userService

    def list(params) {
        def query = User.where {
            isDeleted == false
        }
        return query.list(params)
    }

    def delete(Serializable id) {
        def user = userService.get(id)
        user.isDeleted = true
        user.save(flush: true)
    }

    def findByName(String name) {
        return User.findByUsername(name)
    }
}
