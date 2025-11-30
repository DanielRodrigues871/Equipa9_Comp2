package com.upt.lp.portalestagios.dto.area;

import java.util.UUID;

public class AreaEstagioResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;

    public AreaEstagioResponseDTO() {}

    public AreaEstagioResponseDTO(UUID id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

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
