package com.chat.security;


import com.google.common.base.Optional;
import com.chat.core.Token;

//import io.dropwizard.auth.AuthenticationException;
//import io.dropwizard.auth.Authenticator;

// dont use DatabaseManager

//public class BasicAuthenticator implements Authenticator<String, Token> {
public class BasicAuthenticator {
    private static final int TOKEN_EXPIRY_MILLIS = 432000000; // 5 days
    /*private final DatabaseManager database;

    public BasicAuthenticator(DatabaseManager database) {
        this.database = database;
    }

    @Override
    public Optional<Token> authenticate(String s) throws AuthenticationException {
        Token token = database.getTokens().findByUuid(s);
        if (token != null) {
            long timeDelta = System.currentTimeMillis() - token.getCreated().getTime();
            if (timeDelta < TOKEN_EXPIRY_MILLIS) {
                return Optional.of(token);
            } else {
                database.getTokens().delete(token);
            }
        }
        return Optional.absent();
    }
*/
}
