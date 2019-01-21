package firstgrails

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.core.GrailsApplication
import org.springframework.context.ApplicationContext

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        ApplicationContext app = GrailsApp.run(Application, args)

        CardReaderSystem cardReaderSystem = new CardReaderSystem(app)
        Thread cardSystem = new Thread(cardReaderSystem, "Card reader Thread")
        cardSystem.start()
    }
}