package fr.mbds.firstgrails

import grails.gorm.transactions.Transactional

@Transactional
class StudentCustomService {

    def getStudentByNfcData(String data) {
        // We have to remove the last character as it is a space and it's not stored in th database
        data = data.substring(0, data.size()-1)

        print(data)
        print "\n\n"

        def query = Student.where {
            nfcUid == data
        }

        if (query.list().size() > 0) {
            return query.list().first()
        }
        return null
    }
}
