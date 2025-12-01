package com.upt.lp.componente2.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "area_estagio")
public class AreaEstagio {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", length = 500)
    private String descricao;

    public AreaEstagio() {
        this.id = UUID.randomUUID().toString();
    }

    public AreaEstagio(String nome, String descricao) {
        this();
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da área é obrigatório.");
        }
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e setters
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
            throw new IllegalArgumentException("O nome da área é obrigatório.");
        }
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaEstagio)) return false;
        AreaEstagio that = (AreaEstagio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AreaEstagio{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
