package com.upt.lp.portalestagios.dto.curso;

public class CursoRequestDTO {

    private String nome;
    private String codigo;
    private Integer duracaoAnos;
    private String grau;

    private String departamentoId; // para associar ao departamento

    public CursoRequestDTO() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getDuracaoAnos() {
        return duracaoAnos;
    }
    public void setDuracaoAnos(Integer duracaoAnos) {
        this.duracaoAnos = duracaoAnos;
    }

    public String getGrau() {
        return grau;
    }
    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }
    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }
}

