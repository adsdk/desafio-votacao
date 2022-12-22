package com.br.vote.controller.api;

import com.br.vote.domain.Session;
import com.br.vote.domain.requests.SessionRequest;
import com.br.vote.domain.requests.VoteRequest;
import com.br.vote.domain.responses.VoteFinishedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Sessão", description = "API para operações relacionadas as sessões de votação de uma pauta.")
public interface SessionControllerApi {

    @Operation(
            summary = "Cadastrar sessão.",
            description = "Cadastra uma nova sessão de votação na pauta.",
            tags = {"Sessão"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Sessão cadastrada com sucesso."),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ocorreu algum erro de validação para salvar a sessão.")
            })
    Mono<Void> create(
            @Parameter(description = "Id da pauta.") String agendaId,
            @Parameter(description = "Dados necessários para cadastrar a sessão.") SessionRequest request);

    @Operation(
            summary = "Adicionar voto.",
            description = "Adiciona um novo voto na sessão.",
            tags = {"Sessão"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Sessão cadastrada com sucesso."),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ocorreu algum erro de validação para realizar o voto.")
            })
    Mono<Void> createVote(
            @Parameter(description = "Id da sessão") String sessionId,
            @Parameter(description = "Id do associado") String associateId,
            @Parameter(description = "Dados necessários para realizar o voto.") VoteRequest request);

    @Operation(
            summary = "Buscar sessões ativas.",
            description = "Retorna as sessões disponíveis para voto.",
            tags = {"Sessão"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Sessões ativas retornadas com sucesso."),
            })
    Flux<Session> findActive();

    @Operation(
            summary = "Buscar todas as sessões.",
            description = "Retorna todas as sessões cadastradas.",
            tags = {"Sessão"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Todas as sessões retornadas com sucesso."),
            })
    Flux<Session> findAll();

    @Operation(
            summary = "Buscar os detalhes de uma sessão.",
            description = "Retorna os detalhes de votos de uma sessão.",
            tags = {"Sessão"})
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Detalhes da sessão retornado com sucesso."),
            })
    Mono<VoteFinishedResponse> details(@Parameter(description = "Id da sessão") String sessionId);
}
