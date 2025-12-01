package com.upt.lp.componente2.dto;

public class CursoDTO {
    private String id;
    private String nome;
    private String codigo;
    private String coordenadorId;
    private String coordenadorNome;
    private String departamentoId;
    private String departamentoNome;
    private int duracaoAnos;
    private String grau;

    public CursoDTO() {
    }

    public CursoDTO(String id, String nome, String codigo, int duracaoAnos, String grau) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoAnos = duracaoAnos;
        this.grau = grau;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getCoordenadorId() { return coordenadorId; }
    public void setCoordenadorId(String coordenadorId) { this.coordenadorId = coordenadorId; }

    public String getCoordenadorNome() { return coordenadorNome; }
    public void setCoordenadorNome(String coordenadorNome) { this.coordenadorNome = coordenadorNome; }

    public String getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(String departamentoId) { this.departamentoId = departamentoId; }

    public String getDepartamentoNome() { return departamentoNome; }
    public void setDepartamentoNome(String departamentoNome) { this.departamentoNome = departamentoNome; }

    public int getDuracaoAnos() { return duracaoAnos; }
    public void setDuracaoAnos(int duracaoAnos) { this.duracaoAnos = duracaoAnos; }

    public String getGrau() { return grau; }
    public void setGrau(String grau) { this.grau = grau; }
}
