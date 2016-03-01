package net.apporbit.lab.bot.util.infrastructure.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


/**
 * Backend admin username password authentication token.
 *
 */
@SuppressWarnings("serial")
public class BackendAdminUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    /**
     * Parameterized constructor.
     * @param principal to set
     * @param credentials to set
     */
    public BackendAdminUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
