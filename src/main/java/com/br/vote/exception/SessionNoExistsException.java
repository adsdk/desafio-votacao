package com.br.vote.exception;

public class SessionNoExistsException extends DefaultApiException {

    public SessionNoExistsException() {
        super("Sessão não encontrada, não é possível registrar o voto.");
    }
}
