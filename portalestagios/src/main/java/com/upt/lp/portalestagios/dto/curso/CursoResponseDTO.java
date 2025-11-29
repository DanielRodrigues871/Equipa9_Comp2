package com.upt.lp.portalestagios.dto.curso;

import java.util.UUID;

public class CursoResponseDTO {

    private UUID id;
    private String nome;
    private String codigo;
    private Integer duracaoAnos;
    private String grau;

    private String departamentoNome;

    public CursoResponseDTO() {}

    public CursoResponseDTO(UUID id, String nome, String codigo, Integer duracaoAnos,
                            String grau, String departamentoNome) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoAnos = duracaoAnos;
        this.grau = grau;
        this.departamentoNome = departamentoNome;
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
    public Integer getDuracaoAnos() {
        return duracaoAnos;
    }
    public String getGrau() {
        return grau;
    }
    public String getDepartamentoNome() {
        return departamentoNome;
    }
}

