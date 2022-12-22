package com.br.vote.domain.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaRequest {

    @NotBlank(message = "Título da pauta não informado.")
    private String title;

    @NotBlank(message = "Descrição da pauta não informada.")
    private String description;
}
