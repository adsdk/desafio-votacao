package com.br.vote.mapper;

import com.br.vote.domain.Agenda;
import com.br.vote.domain.requests.AgendaRequest;

public class AgendaMapper {

    public static Agenda toAgenda(AgendaRequest request) {
        return Agenda.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }
}
