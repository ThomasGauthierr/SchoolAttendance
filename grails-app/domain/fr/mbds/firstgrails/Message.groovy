package fr.mbds.firstgrails

class Message {

    User author
    User target
    String content


    static constraints = {
//        author nullable: false
//        target nullable: false
//        content blank: false
    }
}
