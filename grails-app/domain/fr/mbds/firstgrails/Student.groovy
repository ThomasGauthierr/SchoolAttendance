package fr.mbds.firstgrails

class Student {
    int student_number
    String nfcUid
    String firstName
    String lastName
    String address
    String mail
    Date dateCreated

    static hasMany = [
        courses:Course,
        participations:Participation
    ]

    static mapping = {
        courses joinTable:[name:"mm_student_courses", key:'student_id' ]
    }

    static constraints = {
        student_number(nullable: false, blank: false, unique: true)
        nfcUid(nullable: false, blank: false, unique: true)
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        address(nullable: false, blank: false)
        mail(nullable: false, blank: false)
        participations(nullable: true)
    }
}
