package com.br.vote.mapper;

import com.br.vote.domain.Vote;
import com.br.vote.domain.requests.VoteRequest;

public class VoteMapper {

    public static Vote toVote(VoteRequest request) {
        return Vote.builder().choice(request.getChoice()).build();
    }
}
