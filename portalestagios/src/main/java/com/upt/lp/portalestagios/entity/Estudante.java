package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudante")
public class Estudante extends Utilizador {

    @Column(name = "numero_estudante", unique = true, length = 50)
    private String numeroEstudante;

    @Column(name = "ano_matricula")
    private Integer anoMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturas = new ArrayList<>();

    public Estudante() { super(); }
    public Estudante(String nome, String email, String password, String numeroEstudante, Integer anoMatricula) {
        super(nome, email, password);
        this.numeroEstudante = numeroEstudante;
        this.anoMatricula = anoMatricula;
    }

    public String getNumeroEstudante() { return numeroEstudante; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }
    public Integer getAnoMatricula() { return anoMatricula; }
    public void setAnoMatricula(Integer anoMatricula) { this.anoMatricula = anoMatricula; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public List<Candidatura> getCandidaturas() { return candidaturas; }
    public void adicionarCandidatura(Candidatura c) {
        if (!candidaturas.contains(c)) {
            candidaturas.add(c);
            c.setEstudante(this);
        }
    }
}
