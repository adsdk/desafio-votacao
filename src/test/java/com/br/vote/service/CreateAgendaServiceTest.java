package com.br.vote.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.br.vote.domain.Agenda;
import com.br.vote.domain.requests.AgendaRequest;
import com.br.vote.repository.AgendaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = CreateAgendaService.class)
public class CreateAgendaServiceTest {

    @Autowired
    private CreateAgendaService createAgendaService;

    @MockBean
    private AgendaRepository agendaRepository;

    @Test
    @DisplayName("Deve criar uma pauta com sucesso.")
    public void shouldCreateAgendaSuccess() {
        var agendaRequest = AgendaRequest.builder()
                .title("title test")
                .description("description test")
                .build();
        var agenda = Agenda.builder().id("test").build();

        when(agendaRepository.save(any())).thenReturn(Mono.just(agenda));

        StepVerifier.create(createAgendaService.run(agendaRequest)).verifyComplete();
    }
}
