package com.br.vote.service;

import com.br.vote.domain.Associate;
import com.br.vote.domain.requests.AssociateRequest;
import com.br.vote.exception.DocumentExistsException;
import com.br.vote.mapper.AssociateMapper;
import com.br.vote.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAssociateService {

    private final AssociateRepository associateRepository;

    public Mono<Associate> run(AssociateRequest request) {
        var associate = AssociateMapper.toAssociate(request);
        return associateRepository
                .existsByDocument(associate.getDocument())
                .flatMap(validation -> {
                    if (validation) {
                        log.error("Erro ao criar o associado, cpf={} já cadastrado.", associate.getDocument());
                        return Mono.error(new DocumentExistsException());
                    }
                    return associateRepository.save(associate);
                })
                .doOnNext(a -> log.info("Associado criado com sucesso: id={}", a.getId()));
    }
}
