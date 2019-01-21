package fr.mbds.firstgrails

class Course {
    String name
    User teacher
    Date dateCreated
    Date lastUpdated

    static belongsTo = Student

    static hasMany = [
            students:Student,
            sessions:Session
    ]

    static mapping = {
        sessions fetch: 'join'
    }

    static constraints = {
        name(nullable: false, blank: false, size: 2..40)
        teacher(nullable: false)
    }
}