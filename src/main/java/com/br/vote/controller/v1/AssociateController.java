package com.br.vote.controller.v1;

import com.br.vote.controller.api.AssociateControllerApi;
import com.br.vote.domain.Associate;
import com.br.vote.domain.requests.AssociateRequest;
import com.br.vote.service.CreateAssociateService;
import com.br.vote.service.FindAllAssociateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Usado apenas para cadastrar os cpfs e buscar os ids para teste
@RestController
@RequestMapping("/v1/associate")
@RequiredArgsConstructor
public class AssociateController implements AssociateControllerApi {

    private final CreateAssociateService createAssociateService;
    private final FindAllAssociateService findAllAssociateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Valid @RequestBody AssociateRequest request) {
        return createAssociateService.run(request);
    }

    @GetMapping("/all")
    public Flux<Associate> findAll() {
        return findAllAssociateService.run();
    }
}
