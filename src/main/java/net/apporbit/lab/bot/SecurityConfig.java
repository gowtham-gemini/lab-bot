package net.apporbit.lab.bot;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import net.apporbit.lab.bot.util.infrastructure.externalwebservice.SomeExternalServiceAuthenticator;
import net.apporbit.lab.bot.util.infrastructure.security.AuthenticationFilter;
import net.apporbit.lab.bot.util.infrastructure.security.BackendAdminUsernamePasswordAuthenticationProvider;
import net.apporbit.lab.bot.util.infrastructure.security.DomainUsernamePasswordAuthenticationProvider;
import net.apporbit.lab.bot.util.infrastructure.security.ExternalServiceAuthenticator;
import net.apporbit.lab.bot.util.infrastructure.security.ManagementEndpointAuthenticationFilter;
import net.apporbit.lab.bot.util.infrastructure.security.TokenAuthenticationProvider;
import net.apporbit.lab.bot.util.infrastructure.security.TokenService;
import net.apporbit.lab.bot.util.web.ApiController;

/**
 * Security configuration.
 *
 */
//@Configuration
//@EnableScheduling
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** Admin role. */
    @Value("${backend.admin.role}")
    private String backendAdminRole;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
                anyRequest().authenticated().
                and().
                anonymous().disable().
                exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
                addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    /** Actuator management endpoints.
     * @return String array
     */
    private String[] actuatorEndpoints() {
        return new String[]{
                ApiController.AUTOCONFIG_ENDPOINT,
                ApiController.BEANS_ENDPOINT,
                ApiController.CONFIGPROPS_ENDPOINT,
                ApiController.ENV_ENDPOINT,
                ApiController.MAPPINGS_ENDPOINT,
                ApiController.METRICS_ENDPOINT,
                ApiController.SHUTDOWN_ENDPOINT};
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(domainUsernamePasswordAuthenticationProvider()).
                authenticationProvider(backendAdminUsernamePasswordAuthenticationProvider()).
                authenticationProvider(tokenAuthenticationProvider());
    }

    /**
     * Factory method to get token service.
     * @return TokenService
     */
    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    /**
     * Factory method to get external service authenticator.
     * @return ExternalServiceAuthenticator
     */
    @Bean
    public ExternalServiceAuthenticator someExternalServiceAuthenticator() {
        return new SomeExternalServiceAuthenticator();
    }

    /**
     * Factory method to get domain username password authentication provider.
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
        return new DomainUsernamePasswordAuthenticationProvider(tokenService(), someExternalServiceAuthenticator());
    }

    /**
     * Factory method to get backend admin username password authentication provider.
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider backendAdminUsernamePasswordAuthenticationProvider() {
        return new BackendAdminUsernamePasswordAuthenticationProvider();
    }

    /**
     * Factory method to get  token authentication provider.
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(tokenService());
    }

    /**
     * Unauthorized entrypoint.
     * @return AuthenticationEntryPoint to set
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}