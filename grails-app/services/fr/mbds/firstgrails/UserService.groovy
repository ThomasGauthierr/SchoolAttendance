package fr.mbds.firstgrails

import grails.gorm.services.Service

@Service(User)
interface UserService {

    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)

    User updateProfileImageUrl(Serializable id, Long version, String profileImageName)


}