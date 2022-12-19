package com.br.vote.repository;

import com.br.vote.domain.Agenda;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends ReactiveCrudRepository<Agenda, String> {}
