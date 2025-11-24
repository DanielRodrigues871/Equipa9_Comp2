package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coordenador")
public class Coordenador extends Utilizador {

    @ManyToOne(optional = false)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "coordenador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursosGeridos = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEstagio> ofertasRegistadas = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturasGeridas = new ArrayList<>();

    public Coordenador() {
        // Importante: JPA exige construtor vazio
    }

    public Coordenador(String nome, String email, String password, Departamento departamento) {
        super(nome, email, password);
        this.departamento = departamento;
    }

    public void registarOferta(OfertaEstagio oferta) {
        this.ofertasRegistadas.add(oferta);
        oferta.setCoordenadorResponsavel(this);
    }

    public void aprovarOferta(OfertaEstagio oferta) {
        oferta.aprovar();
    }

    public void rejeitarOferta(OfertaEstagio oferta) {
        oferta.rejeitar();
    }

    public void adicionarCursoGerido(Curso curso) {
        this.cursosGeridos.add(curso);
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Curso> getCursosGeridos() {
        return cursosGeridos;
    }

    public List<OfertaEstagio> getOfertasRegistadas() {
        return ofertasRegistadas;
    }

    public List<Candidatura> getCandidaturasGeridas() {
        return candidaturasGeridas;
    }

    public void adicionarCandidatura(Candidatura candidatura) {
        this.candidaturasGeridas.add(candidatura);
        candidatura.setCoordenadorResponsavel(this);
    }

    @Override
    public String toString() {
        return "Coordenador{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", departamento=" + (departamento != null ? departamento.getNome() : "N/A") +
                ", cursosGeridos=" + cursosGeridos.size() +
                ", ofertasRegistadas=" + ofertasRegistadas.size() +
                '}';
    }
}

