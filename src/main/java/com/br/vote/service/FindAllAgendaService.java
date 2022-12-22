package com.br.vote.service;

import com.br.vote.domain.Agenda;
import com.br.vote.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllAgendaService {

    private final AgendaRepository agendaRepository;

    public Flux<Agenda> run() {
        return agendaRepository.findAll().doOnNext(a -> log.info("Pautas retornadas com sucesso."));
    }
}
