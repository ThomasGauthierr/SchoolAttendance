package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class MessageCustomService {
    def messageService

    def getAllUserMessages(User user) {
        def query = Message.where {
            author == user || target == user
        }
        return query.list()
    }

    def readMessage(Serializable id) {
        def message = messageService.get(id)

        if (message) message.read = true
    }

    def checkRead(long userID, long messageID) {
        def query = Message.where {
            target.id == userID && id == messageID
        }

        try {
            query.first()
            messageService.get(messageID).read = true
        } catch (NoSuchElementException e) {
            //Error is only thrown when the query returns no results,
            //meaning the user reading it isn't the target.
        }
    }
}
