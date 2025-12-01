package com.upt.lp.componente2.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	private LocalDateTime datacriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;
	
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private static final String hashPassword (String plainPassword) {
		return encoder.encode(plainPassword);
	}

	public Utilizador() {
		this.id = UUID.randomUUID().toString();
		this.datacriacao = LocalDateTime.now();
		this.dataAtualizacao = LocalDateTime.now();
	}

	public Utilizador(String nome, String email, String password) {
		if(nome == null || nome.isBlank()) 
			throw new IllegalArgumentException("Nome obrigatório!");
		if(email == null || email.isBlank()) 
			throw new IllegalArgumentException("Email obrigatório!");
		if(!email.contains("@") || !email.matches(".*\\.[a-zA-Z] {2,}$")) 
			throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com domínio(.pt, .com, etc...");
		if(password == null || password.isBlank()) 
			throw new IllegalArgumentException("Password obrigatório!");
		if (!isValidPassword(password))
			throw new IllegalArgumentException("A palavra-passe deve ter pelo menos 8 caracteres, uma maiuscula, um numero e um caracter especial");
		
		this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.email = email;
		this.password = hashPassword(password);
		this.datacriacao = LocalDateTime.now();
		this.dataAtualizacao = LocalDateTime.now();
	}
	
	
	
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
		if (nome == null || nome.isBlank())
			throw new IllegalArgumentException("Nome obrigatório!");
		this.nome = nome;
		this.dataAtualizacao = LocalDateTime.now();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null || email.isBlank())
			throw new IllegalArgumentException("Email Obrigatorio!");
		if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com um domínio (.pt, .com, etc)");
		this.email = email;
		this.dataAtualizacao = LocalDateTime.now();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (!isValidPassword(password))
            throw new IllegalArgumentException("A palavra-passe deve ter pelo menos 8 caracteres, uma maiúscula, um número e um carácter especial.");
        this.password = hashPassword(password);
        this.dataAtualizacao = LocalDateTime.now();
	}

	public LocalDateTime getDatacriacao() {
		return datacriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	@PrePersist
	protected void onCreate() {
		if(this.datacriacao == null) {
			this.datacriacao = LocalDateTime.now();
		}
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

	private static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8)
            return false;
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()\\-+=_.].*");
        return hasUpper && hasDigit && hasSpecial;
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Utilizador [id=" + id + ", nome=" + nome + ", email=" + email + ", password=" + password
				+ ", datacriacao=" + datacriacao + ", dataAtualizacao=" + dataAtualizacao + "]";
	}
	
}
