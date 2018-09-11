package fr.mbds.firstgrails

class User {

    String username
    String password

    static constraints = {
        username blank: false
        password blank: false
    }
}
