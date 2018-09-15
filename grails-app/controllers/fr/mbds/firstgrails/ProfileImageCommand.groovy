package fr.mbds.firstgrails

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ProfileImageCommand implements  Validateable {

    MultipartFile profileImageFile
    Long id
    Integer version

    static constrains = {
        id nullable: false
        version nullable: false
        profileImageFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['jpeg', 'jpg', 'png'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
