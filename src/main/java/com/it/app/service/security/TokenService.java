package com.it.app.service.security;

import org.springframework.security.core.Authentication;

/**
 * A Service for Token
 */
public interface TokenService {
    /**
     * Generates token
     *
     * @param authentication
     * @return String
     */
    String generate(Authentication authentication);

    /**
     * Refreshs token
     *
     * @param token - token
     * @return String
     */
    String refresh(String token);

    /**
     * Extracts username from token
     *
     * @param token - token
     * @return String
     */
    String extractUsername(String token);

    /**
     * Validates token
     *
     * @param authToken - not yet validate token
     * @return boolean
     */
    boolean validate(String authToken);
}
