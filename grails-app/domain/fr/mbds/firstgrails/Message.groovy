package fr.mbds.firstgrails

class Message {

    Player author
    Player target
    String content
    Date dateCreated
    Boolean read = Boolean.FALSE

    static constraints = {
        author nullable: false
        target nullable: false
        content blank: false
    }
}
