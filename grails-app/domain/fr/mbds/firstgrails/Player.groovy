package fr.mbds.firstgrails

class Player {

    String username
    Date dateCreated  //dateCreated --> Automatically created date

    static constraints = {
        unsername blank: false, unique: true
    }
}
