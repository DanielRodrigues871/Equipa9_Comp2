package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coordenador> coordenadores = new ArrayList<>();

    public Departamento() {}

    public Departamento(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
        curso.setDepartamento(this);
    }

    public void adicionarCoordenador(Coordenador coordenador) {
        this.coordenadores.add(coordenador);
        coordenador.setDepartamento(this);
    }

    // Getters e Setters
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Coordenador> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(List<Coordenador> coordenadores) {
        this.coordenadores = coordenadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cursos=" + cursos.size() +
                '}';
    }
}
