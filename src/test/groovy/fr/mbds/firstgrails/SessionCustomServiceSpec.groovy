package fr.mbds.firstgrails

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class SessionCustomServiceSpec extends Specification implements ServiceUnitTest<SessionCustomService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
