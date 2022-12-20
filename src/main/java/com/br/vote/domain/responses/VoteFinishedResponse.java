package com.br.vote.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteFinishedResponse {

    private String sessionId;
    private Long votesYes;
    private Long votesNo;
    private Integer totalVotes;
}
