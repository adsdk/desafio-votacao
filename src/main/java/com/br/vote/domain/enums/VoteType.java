package com.br.vote.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteType {
    YES("SIM"),
    NO("NAO");

    @JsonValue
    private String type;
}
