package fr.mbds.firstgrails

class Match {

    User winner
    int winnerScore
    User looser
    int looserScore
    Date dateCreated

    static constraints = {
        winner nullable: true
        looser nullable: true
        winnerScore nullable: true
        looserScore nullable: true
    }


}
