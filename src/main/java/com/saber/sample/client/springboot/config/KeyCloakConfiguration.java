package com.saber.sample.client.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class KeyCloakConfiguration {
    @Value(value = "${service.keycloak.authUrl}")
    private String authUrl;
    @Value(value = "${service.keycloak.realm}")
    private String realm;
    @Value(value = "${service.keycloak.clientId}")
    private String clientId;
    @Value(value = "${service.keycloak.clientSecret}")
    private String clientSecret;
    @Value(value = "${service.keycloak.username}")
    private String username;
    @Value(value = "${service.keycloak.password}")
    private String password;

    @Bean
    public Keycloak keycloak() {
       return KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(username)
                .password(password)
                .build();
    }

}
