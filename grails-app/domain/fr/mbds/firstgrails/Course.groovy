package fr.mbds.firstgrails

class Course {
    String name
    Date date
    User teacher
    Date dateCreated
    Date lastUpdated

    static belongsTo = Person
    static hasMany = [
            users:Person,
            sessions:Session
    ]

    static mapping = {
        users joinTable:[name:"mm_student_courses", key:'course_id' ]
    }

    static constraints = {
        name(nullable: false, blank: false, size: 2..40)
        date(nullable: false)
        teacher(nullable: false)
    }
}