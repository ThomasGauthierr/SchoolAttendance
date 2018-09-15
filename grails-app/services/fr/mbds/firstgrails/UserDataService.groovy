package fr.mbds.firstgrails

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@SuppressWarnings(['LineLength', 'UnusedVariable', 'SpaceAfterOpeningBrace', 'SpaceBeforeClosingBrace'])
@Service(User)
interface UserDataService {

    User get(Long id)

    List<User> list(Map args)

    Number count()

    void delete(Serializable id)

    User save(String name)

    User updateName(Serializable id, Long version, String name)

    User updateFeaturedImageUrl(Serializable id, Long version, String featuredImageUrl)

}
