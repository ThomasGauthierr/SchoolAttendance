package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class UserCustomService {

    def delete(Serializable id) {

    }

    def findByName(String name) {
        return User.findByUsername(name)
    }
}
