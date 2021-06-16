package de.tiupe.oauth2authorizationserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${user.oauth.clientId}")
    private String ClientID;
    @Value("${user.oauth.clientSecret}")
    private String ClientSecret;
    @Value("${user.oauth.redirectUris}")
    private String RedirectURLs;

    private final PasswordEncoder passwordEncoder;

    public AuthServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /*
    * Hier werde die Einstellungen für den Authorization-Server
    * festgelegt. Alle Anfragen mit einem Token sind erlaubt, wenn das Token verifiziert werden kann.*/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /*
    * Festlegen der Client-Eigenschaften. ClientID und clientSecret werden bei den jeweiligen
    * Aufrufen geprüft.
    *
    * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(ClientID)
                .secret(passwordEncoder.encode(ClientSecret))
                .authorizedGrantTypes("authorization_code")
                .scopes("user_info")
                .autoApprove(true)
                .redirectUris(RedirectURLs);
    }

}
