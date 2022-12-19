package com.br.vote.exception;

public class AgendaNoExistsException extends DefaultApiException {

    public AgendaNoExistsException() {
        super("A pauta informada não existe, não é possível cadastrar uma sessão.");
    }
}
