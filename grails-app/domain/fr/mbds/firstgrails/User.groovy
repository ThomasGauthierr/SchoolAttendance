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
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String profileImageName
    Date lastConnection
    Date previousConnection
    boolean isDeleted = false

    static hasMany = [
            matchWon: Match,
            matchLost: Match,
            messageSent: Message,
            messageReceived: Message
    ]

    static mappedBy = [
            matchWon: "winner",
            matchLost: "looser",
            messageSent: "author",
            messageReceived: "target"
    ]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        profileImageName nullable: true, blank: false
        lastConnection nullable: true
        previousConnection nullable: true
        isDeleted nullable: false
    }

    static mapping = {
	    password column: '`password`'
    }

    @Override
    String toString() {
        return username
    }
}
