package com.techmove.fixnow.iam.application.internal.outboundservices.tokens;


public interface TokenService {

    /**
     * This method is responsible for generating a token from a username.
     * @param username the username
     * @return String the token
     */
    String generateToken(String username);

    /**
     * This method is responsible for extracting the username from a token.
     * @param token the token
     * @return String the username
     */
    String getUsernameFromToken(String token);

    /**
     * This method is responsible for validating a token.
     * @param token the token
     * @return boolean true if the token is valid, false otherwise
     */
    boolean validateToken(String token);
}