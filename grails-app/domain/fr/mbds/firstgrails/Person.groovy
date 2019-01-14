package fr.mbds.firstgrails

class Person {
    String firstName
    String lastName
    String address
    String mail

    static belongsTo = [
            teacher:User,
            student:Student
    ]

    static constraints = {
        firstName(nullable: false, blank: false)
        lastName(nullable: false, blank: false)
        address(nullable: true)
        mail(nullable: false, blank: false)
        teacher(nullable: true)
        student(nullable: true)
    }
}
