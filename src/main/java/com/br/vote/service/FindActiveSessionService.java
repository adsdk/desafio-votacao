package com.br.vote.service;

import com.br.vote.domain.Session;
import com.br.vote.repository.SessionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FindActiveSessionService {

    private final SessionRepository sessionRepository;

    public Flux<Session> run() {
        var now = LocalDateTime.now();
        return sessionRepository.findByEndDateGreaterThanEqual(now);
    }
}
