package com.br.vote.service;

import com.br.vote.domain.requests.SessionRequest;
import com.br.vote.exception.AgendaHasActiveSessionException;
import com.br.vote.exception.AgendaNoExistsException;
import com.br.vote.mapper.SessionMapper;
import com.br.vote.repository.AgendaRepository;
import com.br.vote.repository.SessionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateSessionService {

    private final SessionRepository sessionRepository;
    private final AgendaRepository agendaRepository;

    public Mono<Void> run(String agendaId, SessionRequest request) {
        var session = SessionMapper.toSession(request);
        return agendaRepository
                .existsById(agendaId)
                .flatMap(validationExistsAgenda -> {
                    if (!validationExistsAgenda) {
                        log.info("Erro ao criar sessão: agenda={} não existe.", agendaId);
                        return Mono.error(new AgendaNoExistsException());
                    }
                    var now = LocalDateTime.now();
                    return sessionRepository.existsByAgendaIdAndEndDateGreaterThanEqual(agendaId, now);
                })
                .flatMap(validationAgendaHasSession -> {
                    if (validationAgendaHasSession) {
                        log.info("Erro ao criar sessão: agenda={}, já possui sessão ativa.", agendaId);
                        return Mono.error(new AgendaHasActiveSessionException());
                    }
                    return agendaRepository.findById(agendaId);
                })
                .flatMap(agenda -> {
                    session.setAgenda(agenda);
                    return sessionRepository.save(session);
                })
                .doOnNext(s -> log.info("Sessão criada com sucesso: id={}", s.getId()))
                .then();
    }
}
