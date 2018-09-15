package fr.mbds.firstgrails

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

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

        String filename = cmd.profileImageFile.originalFilename
        String folderPath = "${cdnFolder}"
        File folder = new File(folderPath + '\\' + filename)
        folder.createNewFile()
        println(folderPath + '\\' + filename)
        /*
        if(!folder.exists()) {
            folder.mkdirs()
        }*/
        println "cmd id:  " + cmd.id + ". filename: " + filename
        //String path = "${folderPath}/${filename}"
        cmd.profileImageFile.transferTo(new File(folderPath + '\\' + filename))

        String profileImageUrl = "${cdnRootFolder}/${filename}"
        User user = userService.updateProfileImageUrl(cmd.id, cmd.version, profileImageUrl)

        /*if(!user || user.hasErrors()) {
            File f = new File(path)
            f.delete()
        }*/

        user
    }

   /* def serviceMethod() {

    }*/


}
