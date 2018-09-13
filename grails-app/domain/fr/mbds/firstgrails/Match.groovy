package fr.mbds.firstgrails

class Match {

    User winner
    int winnerScore
    User looser
    int looserScore

    static constraints = {
        winner nullable: false
        looser nullable: false
        winnerScore nullable: false
        looserScore nullable: false
    }
}
