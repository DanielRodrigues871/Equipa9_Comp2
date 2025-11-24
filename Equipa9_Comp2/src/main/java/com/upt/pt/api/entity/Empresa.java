package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.upt.pt.api.security.NifUtils;

/**
 * Classe que representa uma empresa
 */
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nif", unique = true)
    private String nif;

    @Column(name = "morada")
    private String morada;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "descricao")	
    private String descricao;

    @Column(name = "ativa")
    private boolean ativa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RepresentanteEmpresa> representantes = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OfertaEstagio> ofertasSubmetidas = new ArrayList<>();

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    public Empresa() {
        this.id = UUID.randomUUID().toString();
        this.ativa = true;
        this.dataCriacao = LocalDateTime.now();
    }

    public Empresa(String nome, String nif, String email, String morada) {
        this();
        
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }
        if (!NifUtils.isNifValido(nif)) {
            throw new IllegalArgumentException("NIF inválido!");
        }
        if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com um domínio (.pt, .com, etc)");
        }
        
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.morada = morada;
    }

    public void adicionarRepresentante(RepresentanteEmpresa representante) {
        this.representantes.add(representante);
        representante.setEmpresa(this);
    }

    public void submeterOferta(OfertaEstagio oferta) {
        this.ofertasSubmetidas.add(oferta);
        oferta.setEmpresa(this);
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
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }
		this.nome = nome;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		if (!NifUtils.isNifValido(nif)) {
            throw new IllegalArgumentException("NIF inválido!");
        }
		this.nif = nif;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido! Deve conter '@' e terminar com um domínio (.pt, .com, etc)");
        }
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public List<RepresentanteEmpresa> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<RepresentanteEmpresa> representantes) {
		this.representantes = representantes;
	}

	public List<OfertaEstagio> getOfertasSubmetidas() {
		return ofertasSubmetidas;
	}

	public void setOfertasSubmetidas(List<OfertaEstagio> ofertasSubmetidas) {
		this.ofertasSubmetidas = ofertasSubmetidas;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
