package com.br.vote.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.br.vote.domain.Session;
import com.br.vote.domain.Vote;
import com.br.vote.domain.enums.VoteType;
import com.br.vote.exception.ActiveSessionException;
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

@SpringBootTest(classes = SessionDetailService.class)
public class SessionDetailServiceTest {

    @Autowired
    private SessionDetailService sessionDetailService;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("Deve mostrar os detalhes da sessao com sucesso.")
    public void shouldSessionDetailSuccess() {
        var sessionId = "test";
        var session = Session.builder()
                .id(sessionId)
                .endDate(LocalDateTime.MIN)
                .votes(List.of(
                        Vote.builder().choice(VoteType.YES).build(),
                        Vote.builder().choice(VoteType.YES).build(),
                        Vote.builder().choice(VoteType.NO).build()))
                .build();

        when(sessionRepository.findById(anyString())).thenReturn(Mono.just(session));

        StepVerifier.create(sessionDetailService.run(sessionId))
                .assertNext(sessionDetail -> {
                    var voteYesCount = 2;
                    var voteNoCount = 1;
                    assertEquals(voteYesCount, sessionDetail.getVotesYes());
                    assertEquals(voteNoCount, sessionDetail.getVotesNo());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar um erro ao detalhar a sessao = [Sessao ativa].")
    public void shouldSessionDetailActiveSessionError() {
        var sessionId = "test";
        var session = Session.builder()
                .id(sessionId)
                .endDate(LocalDateTime.MAX)
                .votes(new ArrayList<>())
                .build();

        when(sessionRepository.findById(anyString())).thenReturn(Mono.just(session));

        StepVerifier.create(sessionDetailService.run(sessionId))
                .expectError(ActiveSessionException.class)
                .verify();
    }
}
