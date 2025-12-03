package com.upt.lp.componente2.enums;

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
