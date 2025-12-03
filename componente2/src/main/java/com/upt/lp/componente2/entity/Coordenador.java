package com.upt.lp.componente2.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "coordenador")
public class Coordenador extends Utilizador {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "coordenador", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Curso> cursosGeridos = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OfertaEstagio> ofertasRegistadas = new ArrayList<>();

    @OneToMany(mappedBy = "coordenadorResponsavel", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Candidatura> candidaturasGeridas = new ArrayList<>();

    public Coordenador() {
        super();
    }

    public Coordenador(String nome, String email, String password, Departamento departamento) {
        super(nome, email, password);
        this.departamento = departamento;
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

    public void setCursosGeridos(List<Curso> cursosGeridos) {
        this.cursosGeridos = cursosGeridos;
    }

    public List<OfertaEstagio> getOfertasRegistadas() {
        return ofertasRegistadas;
    }

    public void setOfertasRegistadas(List<OfertaEstagio> ofertasRegistadas) {
        this.ofertasRegistadas = ofertasRegistadas;
    }

    public List<Candidatura> getCandidaturasGeridas() {
        return candidaturasGeridas;
    }

    public void setCandidaturasGeridas(List<Candidatura> candidaturasGeridas) {
        this.candidaturasGeridas = candidaturasGeridas;
    }

    @Override
    public String toString() {
        return "Coordenador{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", departamento=" + (departamento != null ? departamento.getNome() : "N/A") +
                ", cursosGeridos=" + (cursosGeridos != null ? cursosGeridos.size() : 0) +
                ", ofertasRegistadas=" + (ofertasRegistadas != null ? ofertasRegistadas.size() : 0) +
                '}';
    }
}