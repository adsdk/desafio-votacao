package com.br.vote.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteType {
    YES("SIM"),
    NO("NAO");

    private String type;
}
