package com.br.vote.messaging;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
public class KafkaFinishedVoteSender {

    private final Sinks.Many<Message<String>> sink = Sinks.many().unicast().onBackpressureBuffer();

    @Bean
    public Supplier<Flux<Message<String>>> finishedVotes() {
        return sink::asFlux;
    }

    public void run(String message) {
        var headers = new MessageHeaders(Map.of(KafkaHeaders.KEY, "test".getBytes(StandardCharsets.UTF_8)));
        sink.tryEmitNext(MessageBuilder.createMessage(message, headers));
    }
}
