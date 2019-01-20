package fr.mbds.firstgrails

class Session {

    Date startDate
    Date endDate

    Date dateCreated

    static belongsTo = [
        course:Course
    ]

    static hasMany = [participations:Participation]

    static constraints = {
        startDate nullable: false, blank: false
        endDate nullable: false, blank: false
    }
}
