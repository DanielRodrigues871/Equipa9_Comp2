package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EstudanteDTO {
    private String id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String numeroEstudante;
    private String cursoId;
    private String cursoNome;
    private int anoMatricula;
    private double media;
    private List<String> competencias;
    private List<String> candidaturasIds;
    private List<String> documentosIds;

    public EstudanteDTO() {
    }

    public EstudanteDTO(String id, String nome, String email, LocalDateTime dataCriacao, 
                       LocalDateTime dataAtualizacao, String numeroEstudante, int anoMatricula) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.numeroEstudante = numeroEstudante;
        this.anoMatricula = anoMatricula;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public String getNumeroEstudante() { return numeroEstudante; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getCursoNome() { return cursoNome; }
    public void setCursoNome(String cursoNome) { this.cursoNome = cursoNome; }

    public int getAnoMatricula() { return anoMatricula; }
    public void setAnoMatricula(int anoMatricula) { this.anoMatricula = anoMatricula; }

    public double getMedia() { return media; }
    public void setMedia(double media) { this.media = media; }

    public List<String> getCompetencias() { return competencias; }
    public void setCompetencias(List<String> competencias) { this.competencias = competencias; }

    public List<String> getCandidaturasIds() { return candidaturasIds; }
    public void setCandidaturasIds(List<String> candidaturasIds) { this.candidaturasIds = candidaturasIds; }

    public List<String> getDocumentosIds() { return documentosIds; }
    public void setDocumentosIds(List<String> documentosIds) { this.documentosIds = documentosIds; }
}
