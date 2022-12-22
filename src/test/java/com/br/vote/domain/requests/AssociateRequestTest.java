package com.br.vote.domain.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssociateRequestTest extends RequestTest {

    @Test
    @DisplayName("Testa a validacao dos campos da request.")
    public void shouldValidateRequest() {
        var associateRequest = AssociateRequest.builder().cpf("00000000000").build();
        var errors = validator.validate(associateRequest);
        var countErrors = 0;
        assertEquals(Boolean.TRUE, errors.isEmpty());
        assertEquals(countErrors, errors.size());

        var associateInvalidLengthRequest =
                AssociateRequest.builder().cpf("000").build();
        var errorsAssociateInvalidLength = validator.validate(associateInvalidLengthRequest);
        var countErrorsAssociateInvalidLength = 1;
        assertEquals(Boolean.FALSE, errorsAssociateInvalidLength.isEmpty());
        assertEquals(countErrorsAssociateInvalidLength, errorsAssociateInvalidLength.size());

        var associateNoOnlyNumbersRequest =
                AssociateRequest.builder().cpf("0000000000A").build();
        var errorsAssociateNoOnlyNumbers = validator.validate(associateNoOnlyNumbersRequest);
        var countErrorsAssociateNoOnlyNumbers = 1;
        assertEquals(Boolean.FALSE, errorsAssociateNoOnlyNumbers.isEmpty());
        assertEquals(countErrorsAssociateNoOnlyNumbers, errorsAssociateNoOnlyNumbers.size());
    }
}
