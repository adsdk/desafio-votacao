package com.br.vote.repository;

import com.br.vote.domain.Session;
import java.time.LocalDateTime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SessionRepository extends ReactiveCrudRepository<Session, String> {

    Mono<Boolean> existsByAgendaIdAndEndDateGreaterThanEqual(String agendaId, LocalDateTime date);

    Flux<Session> findByEndDateLessThanAndSyncedFalse(LocalDateTime localDateTime);

    Flux<Session> findByEndDateGreaterThanEqual(LocalDateTime date);

    Flux<Session> findByAgendaId(String agendaId);
}
