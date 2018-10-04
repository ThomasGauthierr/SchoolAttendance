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

    //Used by MessageJob
    def deleteReadMessages() {
        Message.where { read == true }.deleteAll()
    }

    def findReadMessages() {
        Message.where {read == true}
    }

    def findNonReadMessages() {
        Message.where {read == false}
    }

    def findReadMessageByUserId(long userId) {
        Message.where {read == true && (author.id == userId || target.id == userId)}
    }

    def findNonReadMessagesByUserId(long userId) {
        Message.where {read == false && (author.id == userId || target.id == userId)}
    }

    def findMessagesByUsername(String username) {
        def query = Message.where {
            author.username == username || target.username == username
        }
        return query.list()
    }

    def findMessagesByUser(long userId) {
        Message.where {author.id == userId || target.id == userId}
    }
}
