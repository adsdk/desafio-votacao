package com.br.vote.exception;

public class AssociateVotedException extends DefaultApiException {

    public AssociateVotedException() {
        super("Associado já realizou o voto, não é possível votar novamente.");
    }
}
