package com.br.vote.domain;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
@Data
public class Test {

    @Id
    private String id;

    private String name;

    private LocalDateTime created = LocalDateTime.now();
}
