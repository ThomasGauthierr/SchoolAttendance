package fr.mbds.firstgrails

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ProfileImageCommand implements  Validateable {

    //def index() { }
    MultipartFile featuredImageFile
    Long id
    Integer version

    static constrains = {
        id nullable: false
        version nullable: false
        featuredImageFile validator : {val, obj ->
            if(val == null) {
                return false
            }
            if(val == empty) {
                return false
            }

            ['jpeg', 'jpg', 'png'].any {extention ->
                val.originalFilename?.toLowerCase()?.endsWith(extention)
            }
        }
    }
}
