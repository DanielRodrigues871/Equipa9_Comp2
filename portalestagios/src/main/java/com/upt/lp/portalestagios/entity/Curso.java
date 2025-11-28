package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column(name = "duracao_anos")
    private Integer duracaoAnos;

    @Column(name = "grau", length = 50)
    private String grau;

    public Curso() {}
    public Curso(String nome, String codigo, Integer duracaoAnos, String grau) {
        this.nome = nome;
        this.codigo = codigo;
        this.duracaoAnos = duracaoAnos;
        this.grau = grau;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Coordenador getCoordenador() { return coordenador; }
    public void setCoordenador(Coordenador coordenador) { this.coordenador = coordenador; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public Integer getDuracaoAnos() { return duracaoAnos; }
    public void setDuracaoAnos(Integer duracaoAnos) { this.duracaoAnos = duracaoAnos; }
    public String getGrau() { return grau; }
    public void setGrau(String grau) { this.grau = grau; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
