package com.upt.pt.api.enums;

public enum TipoEstagio {
    CURRICULAR("Curricular"),
    EXTRACURRICULAR("Extracurricular");

    private final String descricao;

    TipoEstagio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}