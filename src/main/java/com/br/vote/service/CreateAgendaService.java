package com.br.vote.service;

import com.br.vote.domain.requests.AgendaRequest;
import com.br.vote.mapper.AgendaMapper;
import com.br.vote.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAgendaService {

    private final AgendaRepository agendaRepository;

    public Mono<Void> run(AgendaRequest agendaRequest) {
        var agenda = AgendaMapper.toAgenda(agendaRequest);
        return agendaRepository
                .save(agenda)
                .doOnNext(a -> log.info("Pauta criada com sucesso: id={}", a.getId()))
                .then();
    }
}
