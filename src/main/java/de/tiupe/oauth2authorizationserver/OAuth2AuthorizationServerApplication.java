package de.tiupe.oauth2authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/*
* Die Annotation @EnableResourceServer sorgt dafür, das seine Spring-Security-Filter-Chain für
* jeden einkommenden Request das ankommende OAuth2-Token validiert.
*
* Die Spring-Doku sagt dazu das Folgende:
* ======================================
*   Users should add this annotation and provide a @Bean of type
*   ResourceServerConfigurer (e.g. via ResourceServerConfigurerAdapter) that specifies the details
*   of the resource (URL paths and resource id). In order to use this filter you must
*   @EnableWebSecurity somewhere in your application, either in the same place as you use this annotation,
*   or somewhere else.
* */
@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
public class OAuth2AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
    }
}
