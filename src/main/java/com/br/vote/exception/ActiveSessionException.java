package com.br.vote.exception;

public class ActiveSessionException extends DefaultApiException {

    public ActiveSessionException() {
        super("Sessão ativa, não é possível verificar os detalhes da votação.");
    }
}
