grails.plugin.springsecurity.filterChain.chainMap = [

//Stateless chain
	[
	    pattern: '/api/**',
	    filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
	]

]

grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'grails.tweedledee.AuthenticationToken'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'

grails.plugin.springsecurity.userLookup.userDomainClassName = 'grails.tweedledee.Account'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'grails.tweedledee.AccountRole'
grails.plugin.springsecurity.authority.className = 'grails.tweedledee.Role'

grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
	[
	    [pattern: '/**', access: ['ROLE_READ']]
	]
]