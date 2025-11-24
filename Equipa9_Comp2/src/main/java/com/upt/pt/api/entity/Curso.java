package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um curso
 */
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column(name = "duracao_anos")
    private int duracaoAnos;

    @Column(name = "grau")
    private String grau; // Licenciatura, Mestrado, etc.

    public Curso() {
        this.id = UUID.randomUUID().toString();
    }

    public Curso(String nome, String codigo, int duracaoAnos, String grau) {
        this();
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatório."); 
        }
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do curso é obrigatório."); 
        }
        if (duracaoAnos <= 0) {
            throw new IllegalArgumentException("A duração do curso deve ser maior que zero.");
        }
        if (grau == null || grau.isBlank()) {
            throw new IllegalArgumentException("O grau académico (Licenciatura, Mestrado, etc.) é obrigatório.");
        }
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
    	if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatório."); 
        }
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
    	if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do curso é obrigatório."); 
        }
        this.codigo = codigo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getDuracaoAnos() {
        return duracaoAnos;
    }

    public void setDuracaoAnos(int duracaoAnos) {
    	if (duracaoAnos <= 0) {
            throw new IllegalArgumentException("A duração do curso deve ser maior que zero.");
        }
        this.duracaoAnos = duracaoAnos;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
    	if (grau == null || grau.isBlank()) {
            throw new IllegalArgumentException("O grau académico (Licenciatura, Mestrado, etc.) é obrigatório.");
        }
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
