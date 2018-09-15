package fr.mbds.firstgrails

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import org.apache.commons.io.FilenameUtils

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
    User uploadProfileImage(ProfileImageCommand cmd) {

        def extention = FilenameUtils.getExtension(cmd.profileImageFile.originalFilename)
        String filename = UUID.randomUUID().toString() + '.' + extention
        File folder = new File(cdnFolder + '/' + filename)
        folder.createNewFile()



        cmd.profileImageFile.transferTo(new File(cdnFolder + '/' + filename))

        String profileImageUrl = "${cdnRootFolder}/${filename}"
        User user = userService.updateProfileImageUrl(cmd.id, cmd.version, profileImageUrl)


        user
    }

   /* def serviceMethod() {

    }*/


}
