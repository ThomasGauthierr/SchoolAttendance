package fr.mbds.firstgrails

class Participation {

    boolean delay

    Date dateCreated

    static belongsTo = [
        student:Student,
        session:Session
    ]

    static constraints = {
        delay nullable: false
    }
}
