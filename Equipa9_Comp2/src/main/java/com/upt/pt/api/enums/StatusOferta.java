package com.upt.pt.api.enums;

public enum StatusOferta {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado"),
    ENCERRADO("Encerrado"),
    ARQUIVADA("Arquivada");

    private final String descricao;

    StatusOferta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
