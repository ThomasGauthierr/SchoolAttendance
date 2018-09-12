package fr.mbds.firstgrails

class User {

    String username

    static hasMany = [
            matchWon:Match,
            matchLost:Match,
            messageSent: Message,
            messageReceived: Message
    ]

    static mappedBy = [
            matchWon: "winner",
            matchLost: "looser",
            messageSent: "author",
            messageReceived: "target"
    ]

    static constraints = {

    }
}
