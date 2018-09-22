package fr.mbds.firstgrails

class ApplicationPropertiesTagLib {

    static namespace = 'appProperties'
    def applicationPropertiesGetterService

    def getFileUrl = {
        out << applicationPropertiesGetterService.getWebServerPath()
    }
}
