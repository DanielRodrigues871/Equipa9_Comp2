package com.upt.lp.componente2.enums;

public enum StatusOferta {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado"),
    ENCERRADO("Encerrado");

    private final String descricao;

    StatusOferta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
