package com.br.vote.controller;

import com.br.vote.domain.Test;
import com.br.vote.messaging.KafkaFinishedVoteSender;
import com.br.vote.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final KafkaFinishedVoteSender kafkaFinishedVoteSender;
    private final TestRepository testRepository;

    @PostMapping("/kafka")
    public Mono<Void> testKafka(@RequestBody String message) {
        kafkaFinishedVoteSender.run(message);
        return Mono.empty();
    }

    @PostMapping("/mongo")
    public Mono<Test> testMongo(@RequestBody Test test) {
        return testRepository.save(test);
    }
}
