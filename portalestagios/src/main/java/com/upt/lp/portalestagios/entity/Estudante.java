package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ElementCollection
    @CollectionTable(name = "competencia_estudante", joinColumns = @JoinColumn(name = "estudante_id"))
    @Column(name = "competencia", length = 100)
    private List<String> competencias = new ArrayList<>();

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Candidatura> candidaturas = new ArrayList<>();

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

    public Estudante() {
        super();
    }

    public Estudante(String nome, String email, String password, String numeroEstudante, int anoMatricula) {
        super(nome, email, password);
        this.numeroEstudante = numeroEstudante;
        this.anoMatricula = anoMatricula;
    }

    // Métodos para adicionar aos relacionamentos
    public void adicionarCompetencia(String competencia) {
        this.competencias.add(competencia);
    }

    public void adicionarCandidatura(Candidatura candidatura) {
        this.candidaturas.add(candidatura);
    }

    public void adicionarDocumento(Documento documento) {
        this.documentos.add(documento);
    }

    // Getters e Setters

    public String getNumeroEstudante() {
        return numeroEstudante;
    }

    public void setNumeroEstudante(String numeroEstudante) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudante)) return false;
        if (!super.equals(o)) return false;
        Estudante estudante = (Estudante) o;
        return numeroEstudante.equals(estudante.numeroEstudante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEstudante);
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

