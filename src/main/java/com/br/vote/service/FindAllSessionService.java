package com.br.vote.service;

import com.br.vote.domain.Session;
import com.br.vote.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindAllSessionService {

    private final SessionRepository sessionRepository;

    public Flux<Session> run() {
        return sessionRepository.findAll();
    }
}
