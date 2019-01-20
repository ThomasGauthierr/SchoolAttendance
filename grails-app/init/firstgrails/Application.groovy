package firstgrails

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)

        CardReaderSystem cardReaderSystem = new CardReaderSystem()
        Thread cardSystem = new Thread(cardReaderSystem, "Card reader Thread")
        cardSystem.start()
    }
}