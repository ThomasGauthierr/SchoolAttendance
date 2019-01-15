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
        users joinTable:[name:"mm_student_courses", key:'course_id' ]
    }

    static constraints = {
        name(nullable: false, blank: false, size: 2..40)
        date(nullable: false)
        teacher(nullable: false)
    }
}