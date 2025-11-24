package com.upt.pt.api.enums;

public enum StatusCandidatura {
    SUBMETIDA("Submetida"),
    EM_ANALISE("Em Análise"),
    APROVADA("Aprovada"),
    REJEITADA("Rejeitada");

    private final String descricao;

    StatusCandidatura(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}