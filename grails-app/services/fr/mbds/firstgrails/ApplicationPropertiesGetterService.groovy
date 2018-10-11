package fr.mbds.firstgrails

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class ApplicationPropertiesGetterService {
    GrailsApplication grailsApplication

    // Retrieves the property "tpgrails.fileUrl" in the application.yml config file
    def getWebServerPath() {
        return grailsApplication.config.getProperty('tpgrails.fileUrl')
    }
}
