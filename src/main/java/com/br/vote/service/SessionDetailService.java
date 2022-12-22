package com.br.vote.service;

import com.br.vote.domain.responses.VoteFinishedResponse;
import com.br.vote.exception.ActiveSessionException;
import com.br.vote.mapper.VoteMapper;
import com.br.vote.repository.SessionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionDetailService {

    private final SessionRepository sessionRepository;

    public Mono<VoteFinishedResponse> run(String sessionId) {
        return sessionRepository
                .findById(sessionId)
                .flatMap(session -> {
                    var now = LocalDateTime.now();
                    var activeSession = session.getEndDate().isAfter(now);
                    if (activeSession) {
                        return Mono.error(new ActiveSessionException());
                    }
                    return Mono.just(VoteMapper.toVoteFinishedResponse(session));
                })
                .doOnNext(s -> log.info("Detalhes da sessao={} retornadas com sucesso.", sessionId));
    }
}
