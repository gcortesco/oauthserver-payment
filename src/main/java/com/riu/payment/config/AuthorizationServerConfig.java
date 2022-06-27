package com.riu.payment.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    public static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;



    @Value("${user.oauth.clientId}")
    private String clientId;
    @Value("${user.oauth.clientSecret}")
    private String password;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId)
                .secret("{noop}"+password)
                .authorizedGrantTypes(GRANT_TYPE_CLIENT_CREDENTIALS, GRANT_TYPE_PASSWORD , REFRESH_TOKEN)
                .authorities("ROLE_CLIENT")
                .scopes("payment.read").scopes("payment.write")
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }



}