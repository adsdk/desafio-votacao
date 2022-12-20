package com.br.vote.client;

import static com.br.vote.client.domain.StatusType.ABLE_TO_VOTE;

import com.br.vote.client.domain.ApiValidationResponse;
import com.br.vote.config.ClientProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiValidationClient {

    private final WebClient webClient;
    private final ClientProperties clientProperties;

    public Mono<Boolean> associateAbleToVote(String document) {
        return webClient
                .get()
                .uri(clientProperties.getValidationUrl(), document)
                .retrieve()
                .bodyToMono(ApiValidationResponse.class)
                .map(response -> ABLE_TO_VOTE.equals(response.getStatus()))
                .doOnNext(r -> log.info("Api de validacao retornou com sucesso."));
    }
}
