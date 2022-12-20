package com.br.vote.domain.requests;

import com.br.vote.domain.enums.VoteType;
import lombok.Data;

@Data
public class VoteRequest {

    private VoteType choice;
}
