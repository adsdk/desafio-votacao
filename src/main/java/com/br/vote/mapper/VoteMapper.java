package com.br.vote.mapper;

import static com.br.vote.domain.enums.VoteType.NO;
import static com.br.vote.domain.enums.VoteType.YES;

import com.br.vote.domain.Session;
import com.br.vote.domain.Vote;
import com.br.vote.domain.requests.VoteRequest;
import com.br.vote.domain.responses.VoteFinishedResponse;

public class VoteMapper {

    public static Vote toVote(VoteRequest request) {
        return Vote.builder().choice(request.getChoice()).build();
    }

    public static VoteFinishedResponse toVoteFinishedResponse(Session session) {
        var votesYes = session.getVotes().stream()
                .filter(vote -> YES.equals(vote.getChoice()))
                .count();
        var votesNo = session.getVotes().stream()
                .filter(vote -> NO.equals(vote.getChoice()))
                .count();
        return VoteFinishedResponse.builder()
                .sessionId(session.getId())
                .votesYes(votesYes)
                .votesNo(votesNo)
                .totalVotes(session.getVotes().size())
                .build();
    }
}
