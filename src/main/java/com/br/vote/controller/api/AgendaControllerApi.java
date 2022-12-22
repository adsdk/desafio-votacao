package com.br.vote.controller.api;

import com.br.vote.domain.Agenda;
import com.br.vote.domain.Session;
import com.br.vote.domain.requests.AgendaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Pauta", description = "API para operações relacionadas as pautas.")
public interface AgendaControllerApi {

    @Operation(
            summary = "Cadastrar pauta.",
            description = "Cadastra uma nova pauta.",
            tags = {"Pauta"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Pauta cadastrada com sucesso."),
                @ApiResponse(responseCode = "400", description = "Ocorreu algum erro de validação para salvar a pauta.")
            })
    Mono<Void> create(@Parameter(description = "Dados necessários para cadastrar uma pauta.") AgendaRequest request);

    @Operation(
            summary = "Buscar todas as pautas.",
            description = "Retorna todas as pautas cadastradas.",
            tags = {"Pauta"})
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Todas as pautas cadastradas retornadas com sucessso."),
            })
    Flux<Agenda> findAll();

    @Operation(
            summary = "Buscar sessões de uma pauta.",
            description = "Retorna todas as sessões cadastradas para uma pauta.",
            tags = {"Pauta"})
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Todas as sessões de uma pauta retornadas com sucessso."),
            })
    Flux<Session> findSession(@Parameter(description = "Id da pauta") String agendaId);
}
