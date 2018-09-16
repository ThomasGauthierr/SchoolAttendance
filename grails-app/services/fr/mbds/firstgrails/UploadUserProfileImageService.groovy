package fr.mbds.firstgrails

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import org.apache.commons.io.FilenameUtils
import org.springframework.web.multipart.MultipartFile

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
class UploadUserProfileImageService implements GrailsConfigurationAware{

    UserService userService

    String cdnFolder
    String cdnRootFolder

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getProperty('tpgrails.filePath')
        cdnRootFolder = co.getProperty('tpgrails.fileUrl')
    }

    @SuppressWarnings('JavaToPackageAccess')
    String uploadProfileImage(MultipartFile file) {

        def extention = FilenameUtils.getExtension(file.originalFilename)
        String filename = UUID.randomUUID().toString() + '.' + extention
        File folder = new File(cdnFolder + '/' + filename)
        folder.createNewFile()



        file.transferTo(new File(cdnFolder + '/' + filename))

        filename
    }


}
