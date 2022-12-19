package com.br.vote.exception;

public abstract class DefaultApiException extends RuntimeException {

    public DefaultApiException(String message) {
        super(message);
    }
}
