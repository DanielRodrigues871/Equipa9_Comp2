package com.upt.pt.api.dto;

public class CursoDTO {

    private String id;
    private String nome;
    private String codigo;
    private int duracaoAnos;
    private String grau;

    private String departamentoId;
    private String departamentoNome;

    private String coordenadorId;
    private String coordenadorNome;

    public CursoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getDuracaoAnos() {
        return duracaoAnos;
    }

    public void setDuracaoAnos(int duracaoAnos) {
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

    public String getDepartamentoNome() {
        return departamentoNome;
    }

    public void setDepartamentoNome(String departamentoNome) {
        this.departamentoNome = departamentoNome;
    }

    public String getCoordenadorId() {
        return coordenadorId;
    }

    public void setCoordenadorId(String coordenadorId) {
        this.coordenadorId = coordenadorId;
    }

    public String getCoordenadorNome() {
        return coordenadorNome;
    }

    public void setCoordenadorNome(String coordenadorNome) {
        this.coordenadorNome = coordenadorNome;
    }
}
