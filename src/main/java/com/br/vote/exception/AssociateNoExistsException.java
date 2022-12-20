package com.br.vote.exception;

public class AssociateNoExistsException extends DefaultApiException {

    public AssociateNoExistsException() {
        super("Associado não encontrado, não é possível registrar o voto.");
    }
}
