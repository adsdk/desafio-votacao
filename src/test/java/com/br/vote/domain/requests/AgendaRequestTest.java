package com.br.vote.domain.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgendaRequestTest extends RequestTest {

    @Test
    @DisplayName("Testa a validacao dos campos da request.")
    public void shouldValidateRequest() {
        var agendaRequest =
                AgendaRequest.builder().title("test").description("test").build();
        var errors = validator.validate(agendaRequest);
        var countErrors = 0;
        assertEquals(Boolean.TRUE, errors.isEmpty());
        assertEquals(countErrors, errors.size());

        var agendaErrorEmptyValuesRequest =
                AgendaRequest.builder().title(null).description(null).build();
        var errorsAgendaErrorEmptyValues = validator.validate(agendaErrorEmptyValuesRequest);
        var countErrorsAgendaEmptyValues = 2;
        assertEquals(Boolean.FALSE, errorsAgendaErrorEmptyValues.isEmpty());
        assertEquals(countErrorsAgendaEmptyValues, errorsAgendaErrorEmptyValues.size());
    }
}
