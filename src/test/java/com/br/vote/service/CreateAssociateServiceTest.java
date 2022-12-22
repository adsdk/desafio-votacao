package com.br.vote.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.br.vote.domain.Associate;
import com.br.vote.domain.requests.AssociateRequest;
import com.br.vote.exception.DocumentExistsException;
import com.br.vote.repository.AssociateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = CreateAssociateService.class)
public class CreateAssociateServiceTest {

    @Autowired
    private CreateAssociateService createAssociateService;

    @MockBean
    private AssociateRepository associateRepository;

    @Test
    @DisplayName("Deve criar uma sessao com sucesso.")
    public void shouldCreateAssociateSuccess() {
        var associateRequest = AssociateRequest.builder().cpf("00000000000").build();
        var associate = Associate.builder().id("test").build();

        when(associateRepository.existsByDocument(any())).thenReturn(Mono.just(Boolean.FALSE));
        when(associateRepository.save(any())).thenReturn(Mono.just(associate));

        StepVerifier.create(createAssociateService.run(associateRequest)).verifyComplete();
    }

    @Test
    @DisplayName("Deve retornar erro ao criar o associado = [Ja existe um associado com esse documento].")
    public void shouldCreateAssociateExistsError() {
        var associateRequest = AssociateRequest.builder().cpf("00000000000").build();

        when(associateRepository.existsByDocument(any())).thenReturn(Mono.just(Boolean.TRUE));

        StepVerifier.create(createAssociateService.run(associateRequest))
                .expectError(DocumentExistsException.class)
                .verify();
    }
}
