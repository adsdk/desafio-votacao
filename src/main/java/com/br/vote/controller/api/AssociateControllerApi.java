package com.br.vote.controller.api;

import com.br.vote.domain.Associate;
import com.br.vote.domain.requests.AssociateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Associado", description = "API para operações relacionadas aos associados.")
public interface AssociateControllerApi {

    @Operation(
            summary = "Cadastrar associado.",
            description = "Cadastra um novo associado.",
            tags = {"Associado"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Associado cadastrado com sucesso."),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ocorreu algum erro de validação para salvar o associado.")
            })
    Mono<Void> create(
            @Parameter(description = "Dados necessários para cadastrar um associado.") AssociateRequest request);

    @Operation(
            summary = "Buscar todos os Associados.",
            description = "Retorna todos os Associados cadastrados.",
            tags = {"Associado"})
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Todos os associados cadastrados retornados com sucesso.")
            })
    Flux<Associate> findAll();
}
