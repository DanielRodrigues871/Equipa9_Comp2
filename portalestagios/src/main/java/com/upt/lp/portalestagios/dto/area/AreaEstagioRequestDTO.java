package com.upt.lp.portalestagios.dto.area;

public class AreaEstagioRequestDTO {

    private String nome;
    private String descricao;

    public AreaEstagioRequestDTO() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
