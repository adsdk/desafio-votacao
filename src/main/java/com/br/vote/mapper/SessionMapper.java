package com.br.vote.mapper;

import static java.util.Objects.isNull;

import com.br.vote.domain.Session;
import com.br.vote.domain.requests.SessionRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SessionMapper {

    private static final Integer DEFAULT_MINUTES_TO_VOTE = 1;

    public static Session toSession(SessionRequest request) {
        var now = LocalDateTime.now();
        return Session.builder()
                .startDate(now)
                .endDate(now.plus(validateAndReturnMinutesToVote(request.getMinutesToVote()), ChronoUnit.MINUTES))
                .votes(new ArrayList<>())
                .synced(Boolean.FALSE)
                .build();
    }

    private static Integer validateAndReturnMinutesToVote(Integer minutes) {
        var validate = isNull(minutes) || minutes == 0;
        return validate ? DEFAULT_MINUTES_TO_VOTE : minutes;
    }
}
