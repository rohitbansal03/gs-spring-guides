package com.example.spring.web.exception;

/**
 * Exception in case email already exists for a user
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
