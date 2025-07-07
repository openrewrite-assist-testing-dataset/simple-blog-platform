package com.example.blog.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class ApiKeyAuthenticator implements Authenticator<BasicCredentials, BlogPrincipal> {
    
    private final String validApiKey;

    public ApiKeyAuthenticator(String validApiKey) {
        this.validApiKey = validApiKey;
    }

    @Override
    public Optional<BlogPrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (validApiKey.equals(credentials.getUsername()) && "api-key".equals(credentials.getPassword())) {
            return Optional.of(new BlogPrincipal(credentials.getUsername()));
        }
        return Optional.empty();
    }
}