package com.upt.lp.portalestagios.dto.estudante;

import java.util.UUID;

public class EstudanteResponseDTO {

    private UUID id;

    private String nome;
    private String email;

    private String numeroEstudante;
    private Integer anoMatricula;

    private UUID cursoId;
    private String cursoNome;

    public EstudanteResponseDTO() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumeroEstudante() { return numeroEstudante; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }

    public Integer getAnoMatricula() { return anoMatricula; }
    public void setAnoMatricula(Integer anoMatricula) { this.anoMatricula = anoMatricula; }

    public UUID getCursoId() { return cursoId; }
    public void setCursoId(UUID cursoId) { this.cursoId = cursoId; }

    public String getCursoNome() { return cursoNome; }
    public void setCursoNome(String cursoNome) { this.cursoNome = cursoNome; }
}

