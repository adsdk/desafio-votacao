package com.br.vote.exception;

public class AssociateUnableToVoteException extends DefaultApiException {

    public AssociateUnableToVoteException() {
        super("Associado não está apto para votar.");
    }
}
