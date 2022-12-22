package com.br.vote.service;

import com.br.vote.domain.Associate;
import com.br.vote.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllAssociateService {

    private final AssociateRepository associateRepository;

    public Flux<Associate> run() {
        return associateRepository.findAll().doOnNext(a -> log.info("Associados retornados com sucesso."));
    }
}
