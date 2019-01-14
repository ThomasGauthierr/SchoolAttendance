package fr.mbds.firstgrails

class Session {

    Date date
    Date dateCreated
    Date lastUpdated

    static belongsTo = [
            course:Course,
            students:Student
    ]

    static hasMany = [students:Student]

    static constraints = {
    }
}
