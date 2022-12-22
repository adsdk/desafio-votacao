package com.br.vote.service;

import com.br.vote.domain.Session;
import com.br.vote.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllSessionAgendaService {

    private final SessionRepository sessionRepository;

    public Flux<Session> run(String agendaId) {
        return sessionRepository
                .findByAgendaId(agendaId)
                .doOnNext(s -> log.info("Sessoes da agenda={} retornadas com sucesso.", agendaId));
    }
}
