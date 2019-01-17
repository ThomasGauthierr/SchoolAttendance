package fr.mbds.firstgrails

class Participation {

    Date dateCreated

    static belongsTo = [
        student:Student,
        session:Session
    ]

    static constraints = {
    }
}
