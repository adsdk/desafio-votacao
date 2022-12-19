package com.br.vote.controller;

import com.br.vote.domain.Session;
import com.br.vote.domain.requests.SessionRequest;
import com.br.vote.service.CreateSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final CreateSessionService createSessionService;

    @PostMapping("/{agendaId}")
    public Mono<Session> create(@PathVariable String agendaId, @Valid @RequestBody SessionRequest request) {
        return createSessionService.run(agendaId, request);
    }
}
