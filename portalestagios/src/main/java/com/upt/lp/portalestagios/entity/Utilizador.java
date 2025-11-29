package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

@MappedSuperclass
public abstract class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    protected UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    protected String nome;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "data_criacao", nullable = false)
    protected LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    protected LocalDateTime dataAtualizacao;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /** Hash da password */
    private static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public Utilizador() {
        // Hibernate vai gerar o UUID — não gerar manualmente!
    }

    public Utilizador(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = hashPassword(password);
    }

    // Getters e Setters

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador)) return false;
        Utilizador that = (Utilizador) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
