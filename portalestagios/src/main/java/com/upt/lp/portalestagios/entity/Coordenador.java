package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "coordenador")
public class Coordenador extends Utilizador {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "coordenador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursosGeridos = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEstagio> ofertasRegistadas = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturasGeridas = new ArrayList<>();

    public Coordenador() { super(); }
    public Coordenador(String nome, String email, String password, Departamento departamento) {
        super(nome, email, password);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public List<Curso> getCursosGeridos() { return cursosGeridos; }
    public List<OfertaEstagio> getOfertasRegistadas() { return ofertasRegistadas; }
    public List<Candidatura> getCandidaturasGeridas() { return candidaturasGeridas; }

    // Helpers (mantêm ambos os lados)
    public void adicionarCursoGerido(Curso curso) {
        if (!cursosGeridos.contains(curso)) {
            cursosGeridos.add(curso);
            curso.setCoordenador(this);
        }
    }
    public void removerCursoGerido(Curso curso) {
        if (cursosGeridos.remove(curso)) {
            curso.setCoordenador(null);
        }
    }

    public void registarOferta(OfertaEstagio oferta) {
        if (!ofertasRegistadas.contains(oferta)) {
            ofertasRegistadas.add(oferta);
            oferta.setCoordenadorResponsavel(this);
        }
    }

    public void adicionarCandidatura(Candidatura c) {
        if (!candidaturasGeridas.contains(c)) {
            candidaturasGeridas.add(c);
            c.setCoordenadorResponsavel(this);
        }
    }

    @Override
    public String toString() {
        return "Coordenador{" + getId() + " - " + getNome() + "}";
    }
}
