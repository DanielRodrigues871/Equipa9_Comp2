package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private String id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true, length = 20)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column(name = "duracao_anos", nullable = false)
    private int duracaoAnos;

    @Column(name = "grau", length = 50)
    private String grau; // Exemplo: Licenciatura, Mestrado, etc.

    public Curso() {}

    public Curso(String nome, String codigo, int duracaoAnos, String grau) {
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoAnos = duracaoAnos;
        this.grau = grau;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public int getDuracaoAnos() {
        return duracaoAnos;
    }

    public void setDuracaoAnos(int duracaoAnos) {
        this.duracaoAnos = duracaoAnos;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", grau='" + grau + '\'' +
                '}';
    }
}

