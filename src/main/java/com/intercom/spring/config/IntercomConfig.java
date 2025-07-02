package com.intercom.spring.config;


import io.intercom.api.Intercom;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class IntercomConfig {

    @Value("${intercom.access-token}")
    private String intercomAccessToken;

    @PostConstruct
    public void init() {
        // Initialize the Intercom SDK with the access token read from properties
        Intercom.setToken(intercomAccessToken);
    }
}
