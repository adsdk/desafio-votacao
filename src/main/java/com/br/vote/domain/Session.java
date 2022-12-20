package com.br.vote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Boolean synced;

    @JsonIgnore
    private LocalDateTime syncDate;
}
