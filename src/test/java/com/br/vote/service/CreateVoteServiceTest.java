package com.br.vote.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.br.vote.client.ApiValidationClient;
import com.br.vote.domain.Associate;
import com.br.vote.domain.Session;
import com.br.vote.domain.Vote;
import com.br.vote.domain.enums.VoteType;
import com.br.vote.domain.requests.VoteRequest;
import com.br.vote.exception.AssociateNoExistsException;
import com.br.vote.exception.AssociateUnableToVoteException;
import com.br.vote.exception.AssociateVotedException;
import com.br.vote.exception.FinishedSessionException;
import com.br.vote.repository.AssociateRepository;
import com.br.vote.repository.SessionRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = CreateVoteService.class)
public class CreateVoteServiceTest {

    @Autowired
    private CreateVoteService createVoteService;

    @MockBean
    private SessionRepository sessionRepository;

    @MockBean
    private AssociateRepository associateRepository;

    @MockBean
    private ApiValidationClient apiValidationClient;

    @Test
    @DisplayName("Deve adicionar um voto com sucesso.")
    public void shouldCreateVoteSuccess() {
        var sessionId = "session-test";
        var associateId = "associate-test";
        var voteRequest = VoteRequest.builder().choice(VoteType.YES).build();
        var associate =
                Associate.builder().id(associateId).document("00000000000").build();
        var session = Session.builder()
                .id(sessionId)
                .endDate(LocalDateTime.MAX)
                .votes(new ArrayList<>())
                .build();

        when(associateRepository.findById(anyString())).thenReturn(Mono.just(associate));
        when(apiValidationClient.associateAbleToVote(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(sessionRepository.findById(anyString())).thenReturn(Mono.just(session));
        when(sessionRepository.save(any())).thenReturn(Mono.just(session));

        StepVerifier.create(createVoteService.run(sessionId, associateId, voteRequest))
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar um erro ao adicionar o voto = [Associado nao existe].")
    public void shouldCreateVoteAssociateNoExistsError() {
        var sessionId = "session-test";
        var associateId = "associate-test";
        var voteRequest = VoteRequest.builder().choice(VoteType.YES).build();

        when(associateRepository.findById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(createVoteService.run(sessionId, associateId, voteRequest))
                .expectError(AssociateNoExistsException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve retornar um erro ao adicionar o voto = [Associado nao esta apto para voto].")
    public void shouldCreateVoteAssociateUnableToVoteError() {
        var sessionId = "session-test";
        var associateId = "associate-test";
        var voteRequest = VoteRequest.builder().choice(VoteType.YES).build();
        var associate =
                Associate.builder().id(associateId).document("00000000000").build();

        when(associateRepository.findById(anyString())).thenReturn(Mono.just(associate));
        when(apiValidationClient.associateAbleToVote(anyString())).thenReturn(Mono.just(Boolean.FALSE));

        StepVerifier.create(createVoteService.run(sessionId, associateId, voteRequest))
                .expectError(AssociateUnableToVoteException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve retornar um erro ao adicionar o voto = [Sessao de votacao ja foi finalizada].")
    public void shouldCreateVoteFinishedSessionError() {
        var sessionId = "session-test";
        var associateId = "associate-test";
        var voteRequest = VoteRequest.builder().choice(VoteType.YES).build();
        var associate =
                Associate.builder().id(associateId).document("00000000000").build();
        var session = Session.builder()
                .id(sessionId)
                .endDate(LocalDateTime.MIN)
                .votes(new ArrayList<>())
                .build();

        when(associateRepository.findById(anyString())).thenReturn(Mono.just(associate));
        when(apiValidationClient.associateAbleToVote(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(sessionRepository.findById(anyString())).thenReturn(Mono.just(session));

        StepVerifier.create(createVoteService.run(sessionId, associateId, voteRequest))
                .expectError(FinishedSessionException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve retornar um erro ao adicionar o voto = [Associado ja realizou um voto na sessao].")
    public void shouldVoteAssociateVotedError() {
        var sessionId = "session-test";
        var associateId = "associate-test";
        var voteRequest = VoteRequest.builder().choice(VoteType.YES).build();
        var associate =
                Associate.builder().id(associateId).document("00000000000").build();
        var session = Session.builder()
                .id(sessionId)
                .endDate(LocalDateTime.MAX)
                .votes(List.of(
                        Vote.builder().associate(associate).choice(VoteType.YES).build()))
                .build();

        when(associateRepository.findById(anyString())).thenReturn(Mono.just(associate));
        when(apiValidationClient.associateAbleToVote(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(sessionRepository.findById(anyString())).thenReturn(Mono.just(session));

        StepVerifier.create(createVoteService.run(sessionId, associateId, voteRequest))
                .expectError(AssociateVotedException.class)
                .verify();
    }
}
