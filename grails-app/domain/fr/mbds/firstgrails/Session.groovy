package fr.mbds.firstgrails

class Session {

    Date date
    String beginning
    String end
    Date dateCreated
    Date lastUpdated

    static belongsTo = [
            course:Course,
            students:Student
    ]

    static hasMany = [students:Student]

    static constraints = {
        date nullable: false, blank: false
        beginning nullable: false, blank: false
        end nullable: false, blank: false
    }
}
