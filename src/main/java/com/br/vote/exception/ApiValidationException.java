package com.br.vote.exception;

public class ApiValidationException extends DefaultApiException {

    public ApiValidationException() {
        super("Erro no retorno da API de validação.");
    }
}
