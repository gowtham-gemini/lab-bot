package net.apporbit.lab.bot.util.infrastructure.externalwebservice;

import org.springframework.security.core.authority.AuthorityUtils;

import net.apporbit.lab.bot.domain.entity.DomainUser;
import net.apporbit.lab.bot.util.infrastructure.AuthenticatedExternalWebService;
import net.apporbit.lab.bot.util.infrastructure.security.AuthenticationWithToken;
import net.apporbit.lab.bot.util.infrastructure.security.ExternalServiceAuthenticator;

/**
 * External service authenticator.
 *
 */
public class SomeExternalServiceAuthenticator implements ExternalServiceAuthenticator {

    @Override
    public AuthenticationWithToken authenticate(String username, String password) {
        ExternalWebServiceStub externalWebService = new ExternalWebServiceStub();

        // Do all authentication mechanisms required by external web service protocol and validated response.
        // Throw descendant of Spring AuthenticationException in case of unsucessful authentication. For example BadCredentialsException

        // ...
        // ...

        // If authentication to external service succeeded then create authenticated wrapper with proper Principal and GrantedAuthorities.
        // GrantedAuthorities may come from external service authentication or be hardcoded at our layer as they are here with ROLE_DOMAIN_USER
        AuthenticatedExternalWebService authenticatedExternalWebService = new AuthenticatedExternalWebService(new DomainUser(username), null,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DOMAIN_USER"));
        authenticatedExternalWebService.setExternalWebService(externalWebService);

        return authenticatedExternalWebService;
    }
}