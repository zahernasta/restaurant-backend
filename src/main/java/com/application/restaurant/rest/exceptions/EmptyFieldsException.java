package com.application.restaurant.rest.exceptions;

public class EmptyFieldsException extends RuntimeException {

    public EmptyFieldsException(String message) {
        super(message);
    }

    public EmptyFieldsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFieldsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
