package com.br.vote.domain.requests;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociateRequest {

    @Pattern(regexp = "([0-9]{11})", message = "CPF do associado não informado ou inválido.")
    private String cpf;
}
