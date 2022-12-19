package com.br.vote.exception;

public class DocumentExistsException extends DefaultApiException {

    public DocumentExistsException() {
        super("CPF já existe, não é possível realizar o cadastro.");
    }
}
