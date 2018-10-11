package fr.mbds.firstgrails

class Match {

    User winner
    Integer winnerScore
    User looser
    Integer looserScore
    Date dateCreated

    static constraints = {
        winner nullable: true
        looser nullable: true
        winnerScore nullable: true
        looserScore nullable: true

    }


}
