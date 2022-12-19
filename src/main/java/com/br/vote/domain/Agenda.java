package com.br.vote.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "agenda")
@Data
@Builder
public class Agenda {

    @Id
    private String id;

    private String title;
    private String description;
}
