package fr.mbds.firstgrails

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class ApplicationPropertiesGetterService {
    GrailsApplication grailsApplication

    def getWebServerPath() {
        return grailsApplication.config.getProperty('tpgrails.fileUrl')
    }
}
