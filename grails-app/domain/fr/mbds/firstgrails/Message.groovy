package fr.mbds.firstgrails

class Message {

    User author
    User target
    String content
    boolean read = false

    static constraints = {
    }
}
