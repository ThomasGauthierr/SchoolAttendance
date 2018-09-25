package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class MessageCustomService {

    def getAllUserMessages(User user) {
        def query = Message.where {
            author == user || target == user
        }
        return query.list()
    }
}
