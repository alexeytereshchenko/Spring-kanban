package io.moren.springkanban.exception;

public class TokenRefreshException extends RuntimeException {

    public TokenRefreshException() {
    }

    public TokenRefreshException(String message) {
        super(message);
    }
}
