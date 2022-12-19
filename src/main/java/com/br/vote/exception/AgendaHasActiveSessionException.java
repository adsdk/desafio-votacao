package com.br.vote.exception;

public class AgendaHasActiveSessionException extends DefaultApiException {

    public AgendaHasActiveSessionException() {
        super("Sessão não cadastrada, já existe uma sessão ativa para essa pauta.");
    }
}
