package fr.mbds.firstgrails

class Course {
    String name
    User teacher
    Date dateCreated
    Date lastUpdated

    String description

    static belongsTo = Student

    static hasMany = [
            sessions:Session
    ]

    static mapping = {
        sessions fetch: 'join'
    }

    static constraints = {
        name(nullable: false, blank: false, size: 2..40)
        teacher(nullable: false)
        description nullable: false, blank: false
    }
}