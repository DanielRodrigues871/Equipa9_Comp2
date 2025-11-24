package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um documento
 */
@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo; // CV, Carta de Motivação, Certificado, etc.

    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @Column(name = "tamanho_bytes")
    private long tamanhoBytes;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    public Documento() {
        this.id = UUID.randomUUID().toString();
        this.dataUpload = LocalDateTime.now();
    }

    public Documento(String nome, String tipo, String caminhoArquivo) {
        this();
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do documento é obrigatório.");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("O tipo do documento é obrigatório.");
        }
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório.");
        }
        this.nome = nome;
        this.tipo = tipo;
        this.caminhoArquivo = caminhoArquivo;
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
            throw new IllegalArgumentException("O nome do documento é obrigatório.");
        }
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("O tipo do documento é obrigatório.");
        }
		this.tipo = tipo;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("O caminho do arquivo é obrigatório.");
        }
		this.caminhoArquivo = caminhoArquivo;
	}

	public long getTamanhoBytes() {
		return tamanhoBytes;
	}

	public void setTamanhoBytes(long tamanhoBytes) {
		this.tamanhoBytes = tamanhoBytes;
	}

	public LocalDateTime getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(LocalDateTime dataUpload) {
		this.dataUpload = dataUpload;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento documento = (Documento) o;
        return Objects.equals(id, documento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dataUpload=" + dataUpload +
                '}';
    }
}
