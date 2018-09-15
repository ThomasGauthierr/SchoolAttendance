package fr.mbds.firstgrails

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
class UploadUserProfileImageService implements GrailsConfigurationAware{

    UserService userDataService

    String cdnFolder
    String cdnRootFolder

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('tpgrails.filePath')
        cdnRootFolder = co.getRequiredProperty('tpgrails.fileUrl')
    }

    @SuppressWarnings('JavaToPackageAccess')
    User uploadProfileImage(ProfileImageCommand cmd) {

        String filename = cmd.featuredImageFile.originalFilename
        String folderPath = "${cdnFolder}/user/${cmd.id}"
        File folder = new File(folderPath)

        if(!folder.exists()) {
            folder.mkdirs()
        }

        String path = "${folderPath}/${filename}"
        cmd.featuredImageFile.transferTo(new File(path))

        String profileImageUrl = "${cdnRootFolder}/user/${cmd.id}/${filename}"
        User user = userDataService.updateProfileImageUrl(cmd.id, cmd.version, profileImageUrl)

        if(!user || user.hasErrors()) {
            File f = new File(path)
            f.delete()
        }

        user
    }

    def serviceMethod() {

    }


}
