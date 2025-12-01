package com.upt.lp.componente2.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "estudante")
public class Estudante extends Utilizador {

    @Column(name = "numero_estudante", nullable = false, unique = true, length = 20)
    private String numeroEstudante;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;
    
    @Column(name = "ano_matricula", nullable = false)
    private int anoMatricula;
    
    @Column(name = "media")
    private Double media;
     
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "competencia_estudante", joinColumns = @JoinColumn(name = "estudante_id"))
    @Column(name = "competencia")
    private List<String> competencias = new ArrayList<>();
    
    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Candidatura> candidaturas = new ArrayList<>();
    
    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Documento> documentos = new ArrayList<>();
    
    public Estudante() {
        super();
    }

    public Estudante(String nome, String email, String password, String numeroEstudante, int anoMatricula) {
        super(nome, email, password);
        
        if (numeroEstudante == null || numeroEstudante.isBlank()) {
            throw new IllegalArgumentException("O número de estudante é obrigatório.");
        }
        if (anoMatricula < 1) {
            throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        }
        
        this.numeroEstudante = numeroEstudante;
        this.anoMatricula = anoMatricula;
        this.media = 0.0;
    }

    public void adicionarCompetencia(String competencia) {
        this.competencias.add(competencia);
    }

    public void adicionarCandidatura(Candidatura candidatura) {
        this.candidaturas.add(candidatura);
        candidatura.setEstudante(this);
    }

    public void adicionarDocumento(Documento documento) {
        this.documentos.add(documento);
        documento.setEstudante(this);
    }
    
    public String getNumeroEstudante() {
        return numeroEstudante;
    }

    public void setNumeroEstudante(String numeroEstudante) {
        if (numeroEstudante == null || numeroEstudante.isBlank()) {
            throw new IllegalArgumentException("O número de estudante é obrigatório.");
        }
        this.numeroEstudante = numeroEstudante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAnoMatricula() {
        return anoMatricula;
    }

    public void setAnoMatricula(int anoMatricula) {
        if (anoMatricula < 1) {
            throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        }
        this.anoMatricula = anoMatricula;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public List<String> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<String> competencias) {
        this.competencias = competencias;
    }

    public List<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public void setCandidaturas(List<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", numeroEstudante='" + numeroEstudante + '\'' +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", anoMatricula=" + anoMatricula +
                ", media=" + media +
                '}';
    }
}
