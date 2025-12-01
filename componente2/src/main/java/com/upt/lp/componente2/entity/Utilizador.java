package com.upt.lp.componente2.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class Utilizador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private String id;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;
    
    // Removido o BCryptPasswordEncoder como campo estático
    // O hashing será tratado no service ou com @PrePersist
    
    public Utilizador() {
        this.id = UUID.randomUUID().toString();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    public Utilizador(String nome, String email, String password) {
        validarDados(nome, email, password);
        
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.password = password; // O hashing será feito no @PrePersist
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    private void validarDados(String nome, String email, String password) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatório!");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email obrigatório!");
        if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com um domínio (.pt, .com, etc)");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password obrigatória!");
        if (!isValidPassword(password))
            throw new IllegalArgumentException("A palavra-passe deve ter pelo menos 8 caracteres, uma maiúscula, um número e um carácter especial.");
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { 
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome obrigatório!");
        this.nome = nome; 
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { 
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email obrigatório!");
        if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com um domínio (.pt, .com, etc)");
        this.email = email; 
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { 
        if (!isValidPassword(password))
            throw new IllegalArgumentException("A palavra-passe deve ter pelo menos 8 caracteres, uma maiúscula, um número e um carácter especial.");
        this.password = password; 
    }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    
    @PrePersist
    protected void onCreate() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
        this.dataAtualizacao = LocalDateTime.now();
        
        // Hash da password antes de persistir
        if (this.password != null && !this.password.startsWith("$2a$")) {
            this.password = hashPassword(this.password);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
        
        // Hash da password se foi alterada e ainda não está hasheada
        if (this.password != null && !this.password.startsWith("$2a$")) {
            this.password = hashPassword(this.password);
        }
    }
    
    private String hashPassword(String plainPassword) {
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = 
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        return encoder.encode(plainPassword);
    }
    
    private static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8)
            return false;
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()\\-+=_.].*");
        return hasUpper && hasDigit && hasSpecial;
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
    
    @Override
    public String toString() {
        return "Utilizador{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}