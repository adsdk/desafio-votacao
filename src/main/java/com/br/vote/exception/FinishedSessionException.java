package com.br.vote.exception;

public class FinishedSessionException extends DefaultApiException {

    public FinishedSessionException() {
        super("Sessão finalizada, não é possível adicionar votos.");
    }
}
