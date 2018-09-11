package fr.mbds.firstgrails

class Match {

    Player playerOne
    Player playerTwo
    Integer score = 0

    static constraints = {
        playerOne nullable: false
        playerTwo nullable: false
        score nullable: false
    }
}
