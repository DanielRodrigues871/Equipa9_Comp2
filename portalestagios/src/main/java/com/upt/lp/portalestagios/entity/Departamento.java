package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "descricao", length = 500)
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

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Curso> getCursos() { return cursos; }
    public List<Coordenador> getCoordenadores() { return coordenadores; }

    public void adicionarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            curso.setDepartamento(this);
        }
    }

    public void adicionarCoordenador(Coordenador c) {
        if (!coordenadores.contains(c)) {
            coordenadores.add(c);
            c.setDepartamento(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
