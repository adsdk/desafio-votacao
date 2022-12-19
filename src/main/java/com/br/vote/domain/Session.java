package com.br.vote.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
@Data
@Builder
public class Session {

    @Id
    private String id;

    private Agenda agenda;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Vote> votes;
    private Boolean synced;
    private LocalDateTime syncDate;
}
