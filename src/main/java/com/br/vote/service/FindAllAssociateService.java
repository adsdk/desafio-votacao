package com.br.vote.service;

import com.br.vote.domain.Associate;
import com.br.vote.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindAllAssociateService {

    private final AssociateRepository associateRepository;

    public Flux<Associate> run() {
        return associateRepository.findAll();
    }
}
