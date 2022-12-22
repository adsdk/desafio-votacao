package com.br.vote.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.br.vote.domain.Agenda;
import com.br.vote.domain.Session;
import com.br.vote.domain.requests.SessionRequest;
import com.br.vote.exception.AgendaHasActiveSessionException;
import com.br.vote.exception.AgendaNoExistsException;
import com.br.vote.repository.AgendaRepository;
import com.br.vote.repository.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = CreateSessionService.class)
public class CreateSessionServiceTest {

    @Autowired
    private CreateSessionService createSessionService;

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private AgendaRepository agendaRepository;

    @Test
    @DisplayName("Deve criar uma sessao com sucesso.")
    public void shouldCreateSessionSuccess() {
        var agenda =
                Agenda.builder().id("test").title("test").description("test").build();
        var sessionRequest = SessionRequest.builder().minutesToVote(5).build();
        var session = Session.builder().id("test").build();

        when(agendaRepository.existsById(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(sessionRepository.existsByAgendaIdAndEndDateGreaterThanEqual(any(), any()))
                .thenReturn(Mono.just(Boolean.FALSE));
        when(agendaRepository.findById(anyString())).thenReturn(Mono.just(agenda));
        when(sessionRepository.save(any())).thenReturn(Mono.just(session));

        StepVerifier.create(createSessionService.run(agenda.getId(), sessionRequest))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar erro ao criar a sessao = [Pauta nao existe].")
    public void shouldCreateSessionAgendaNoExistsError() {
        var agenda =
                Agenda.builder().id("test").title("test").description("test").build();
        var sessionRequest = SessionRequest.builder().minutesToVote(5).build();

        when(agendaRepository.existsById(anyString())).thenReturn(Mono.just(Boolean.FALSE));

        StepVerifier.create(createSessionService.run(agenda.getId(), sessionRequest))
                .expectError(AgendaNoExistsException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve retornar erro ao criar a sessao = [Ja existe uma sessao para a pauta].")
    public void shouldCreateSessionAgendaHasActiveSessionError() {
        var agenda =
                Agenda.builder().id("test").title("test").description("test").build();
        var sessionRequest = SessionRequest.builder().minutesToVote(5).build();

        when(agendaRepository.existsById(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(sessionRepository.existsByAgendaIdAndEndDateGreaterThanEqual(any(), any()))
                .thenReturn(Mono.just(Boolean.TRUE));

        StepVerifier.create(createSessionService.run(agenda.getId(), sessionRequest))
                .expectError(AgendaHasActiveSessionException.class)
                .verify();
    }
}
