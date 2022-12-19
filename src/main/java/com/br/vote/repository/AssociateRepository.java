package com.br.vote.repository;

import com.br.vote.domain.Associate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AssociateRepository extends ReactiveCrudRepository<Associate, String> {

    Mono<Boolean> existsByDocument(String document);
}
