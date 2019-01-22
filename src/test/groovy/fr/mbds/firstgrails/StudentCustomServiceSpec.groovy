package fr.mbds.firstgrails

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class StudentCustomServiceSpec extends Specification implements ServiceUnitTest<StudentCustomService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
