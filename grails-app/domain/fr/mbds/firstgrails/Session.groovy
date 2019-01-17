package fr.mbds.firstgrails

class Session {

    Date date
    Integer startHours
    Integer startMins
    Integer endHours
    Integer endMins

    Date dateCreated

    static belongsTo = [
        course:Course
    ]

    static hasMany = [participations:Participation]

    static constraints = {
        date nullable: false, blank: false
        startMins nullable: false, range: 0..59
        startHours nullable: false, range: 0..23
        endMins nullable: false, range: 0..59
        endHours nullable: false, range: 0..23
    }
}
