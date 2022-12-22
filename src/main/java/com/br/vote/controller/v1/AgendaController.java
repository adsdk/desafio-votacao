package com.br.vote.controller.v1;

import com.br.vote.controller.api.AgendaControllerApi;
import com.br.vote.domain.Agenda;
import com.br.vote.domain.Session;
import com.br.vote.domain.requests.AgendaRequest;
import com.br.vote.service.CreateAgendaService;
import com.br.vote.service.FindAllAgendaService;
import com.br.vote.service.FindAllSessionAgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/agenda")
@RequiredArgsConstructor
public class AgendaController implements AgendaControllerApi {

    private final CreateAgendaService createAgendaService;
    private final FindAllAgendaService findAllAgendaService;
    private final FindAllSessionAgendaService findAllSessionAgendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Valid @RequestBody AgendaRequest request) {
        return createAgendaService.run(request);
    }

    @GetMapping("/all")
    public Flux<Agenda> findAll() {
        return findAllAgendaService.run();
    }

    @GetMapping("{agendaId}/session")
    public Flux<Session> findSession(@PathVariable String agendaId) {
        return findAllSessionAgendaService.run(agendaId);
    }
}
