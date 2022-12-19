package com.br.vote.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "associate")
@Data
@Builder
public class Associate {

    @Id
    private String id;

    private String document;
}
