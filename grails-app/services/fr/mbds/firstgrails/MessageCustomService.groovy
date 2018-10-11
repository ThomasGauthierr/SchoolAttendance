package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class MessageCustomService {
    def messageService

    // Retrieves all messages where the user is the author or target
    def getAllUserMessages(User user) {
        def query = Message.where {
            author == user || target == user
        }
        return query.list()
    }

    // Sets "read" to true to the book whose id is passed in parameter
    def readMessage(Serializable id) {
        def message = messageService.get(id)
        if (message) message.read = true
    }

    // Check if the user (1st parameter) has the right to access to a specific message (2nd parameter)
    def checkAccess(User user, long messageID) {
        def message = messageService.get(messageID)
        if (message.target == user || message.author == user) {
            return true
        } else {
            return false
        }
    }

    // Checks if the user reading the mesage is the target. If it's the case, sets the message as "read".
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

    // Used by MessageJob
    // Deletes all read messages
    def deleteReadMessages() {
        Message.where { read == true }.deleteAll()
    }

    // Returns every read messages
    def findReadMessages() {
        Message.where {read == true}
    }

    // Returns every not-read messages
    def findNonReadMessages() {
        Message.where {read == false}
    }

    // Returns all read messages of a user
    def findReadMessageByUserId(long userId) {
        Message.where {read == true && (author.id == userId || target.id == userId)}
    }

    // Returns all not-read messages of a user
    def findNonReadMessagesByUserId(long userId) {
        Message.where {read == false && (author.id == userId || target.id == userId)}
    }

    // Returns all messages of a user specified by its username
    def findMessagesByUsername(String username) {
        def query = Message.where {
            author.username == username || target.username == username
        }
        return query.list()
    }

    // Returns all messages of a user specified by its ID
    def findMessagesByUser(long userId) {
        Message.where {author.id == userId || target.id == userId}
    }
}
