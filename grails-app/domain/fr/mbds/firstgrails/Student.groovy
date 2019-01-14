package fr.mbds.firstgrails

class Student {
    int student_number
    String NFC_id
    Person person

    static hasMany = [
            courses:Course,
            sessions:Session
    ]

    static mapping = {
        courses joinTable:[name:"mm_student_courses", key:'student_id' ]
    }

    static constraints = {
        student_number(nullable: false, blank: false, unique: true)
        NFC_id(nullable: false, blank: false, unique: true)
        person(nullable: false)
    }
}
