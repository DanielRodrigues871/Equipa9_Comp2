package com.upt.lp.portalestagios.dto.departamento;

import java.util.UUID;

public class DepartamentoResponseDTO {

    private UUID id;
    private String nome;
    private String codigo;
    private String descricao;

    public DepartamentoResponseDTO() {}

    public DepartamentoResponseDTO(UUID id, String nome, String codigo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}

