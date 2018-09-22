// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.firstgrails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.firstgrails.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.firstgrails.Role'
grails.plugin.springsecurity.requestMap.className = 'fr.mbds.firstgrails.UserRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/**/create/**',               access: ['ROLE_ADMIN']],
	[pattern: '/**/delete/**',               access: ['ROLE_ADMIN']],
	[pattern: '/**/edit/**',               access: ['ROLE_ADMIN']],
	[pattern: '/**/role/**',               access: []],
	[pattern: '/**/userRole/**',               access: []],
	[pattern: '/**/user/index',               access: ['ROLE_ADMIN']],
	[pattern: '/**/match/index',               access: ['ROLE_ADMIN']],
	[pattern: '/**/message/index',               access: ['ROLE_ADMIN']],
	[pattern: '/**',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/main/connection'

//Enable logout through GET
grails.plugin.springsecurity.logout.postOnly = false

grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*', '/fonts/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**', '/fonts/**']
