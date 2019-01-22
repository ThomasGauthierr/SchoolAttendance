package fr.mbds.firstgrails

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1
    // Auto generated fields.
    String username
    String password
    String firstName
    String lastName
    String address
    String mail
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String profileImageName = "default-user.jpg"
    Date lastConnection
    Date previousConnection
    Date dateCreated

    boolean isDeleted = false

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        lastConnection nullable: true
        previousConnection nullable: true
        isDeleted nullable: false

        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        address nullable: false, blank: false
        mail nullable: false, blank: false
    }

    static mapping = {
	    password column: '`password`'
    }

    @Override
    String toString() {
        return username
    }
}
