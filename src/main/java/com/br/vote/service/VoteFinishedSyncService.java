package com.br.vote.service;

import com.br.vote.mapper.VoteMapper;
import com.br.vote.messaging.KafkaFinishedVoteSender;
import com.br.vote.repository.SessionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteFinishedSyncService {

    private final KafkaFinishedVoteSender kafkaFinishedVoteSender;
    private final SessionRepository sessionRepository;

    @Scheduled(fixedDelay = 30000)
    public void run() {
        var now = LocalDateTime.now();
        sessionRepository
                .findByEndDateLessThanAndSyncedFalse(now)
                .flatMap(session -> {
                    var voteFinishedResponse = VoteMapper.toVoteFinishedResponse(session);
                    session.setSynced(Boolean.TRUE);
                    session.setSyncDate(now);
                    kafkaFinishedVoteSender.run(voteFinishedResponse);
                    return sessionRepository.save(session);
                })
                .doOnNext(s -> log.info("Dados da sessao={} foram enviadas para o topico.", s.getId()))
                .subscribe();
    }
}
