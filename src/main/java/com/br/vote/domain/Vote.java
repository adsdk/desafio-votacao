package com.br.vote.domain;

import com.br.vote.domain.enums.VoteType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {

    private Associate associate;
    private VoteType choice;
}
