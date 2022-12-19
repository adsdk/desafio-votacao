package com.br.vote.mapper;

import com.br.vote.domain.Associate;
import com.br.vote.domain.requests.AssociateRequest;

public class AssociateMapper {

    public static Associate toAssociate(AssociateRequest request) {
        return Associate.builder().document(request.getCpf()).build();
    }
}
