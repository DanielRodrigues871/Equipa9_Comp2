package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "estagio")
public class Estagio {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaEstagio oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "estado_final")
    private String estadoFinal; // "EM_CURSO", "CONCLUIDO", "CANCELADO"

    @Column(name = "nota_final")
    private String notaFinal;

    @Column(name = "observacoes")
    private String observacoes;

    public Estagio() {
        this.id = UUID.randomUUID().toString();
        this.estadoFinal = "EM_CURSO";
    }

    public Estagio(Estudante estudante, OfertaEstagio oferta, LocalDate dataInicio) {
        this();
        if (estudante == null) {
            throw new IllegalArgumentException("O estudante é obrigatório.");
        }
        if (oferta == null) {
            throw new IllegalArgumentException("A oferta é obrigatória.");
        }
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início é obrigatória.");
        }
        this.estudante = estudante;
        this.oferta = oferta;
        this.curso = estudante.getCurso();
        this.empresa = oferta.getEmpresa();
        this.dataInicio = dataInicio;
    }

    public void concluir(String notaFinal, LocalDate dataFim) {
        this.estadoFinal = "CONCLUIDO";
        this.notaFinal = notaFinal;
        this.dataFim = dataFim;
    }

    public void cancelar(String observacoes) {
        this.estadoFinal = "CANCELADO";
        this.observacoes = observacoes;
    }

    // Getters e Setters 

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		if (estudante == null) {
            throw new IllegalArgumentException("O estudante é obrigatório.");
        }
		this.estudante = estudante;
	}

	public OfertaEstagio getOferta() {
		return oferta;
	}

	public void setOferta(OfertaEstagio oferta) {
		if (oferta == null) {
            throw new IllegalArgumentException("A oferta é obrigatória.");
        }
		this.oferta = oferta;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início é obrigatória.");
        }
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(String estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public String getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(String notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
    public String toString() {
        return "Estagio{" +
                "id='" + id + '\'' +
                ", estudante=" + (estudante != null ? estudante.getNome() : "N/A") +
                ", empresa=" + (empresa != null ? empresa.getNome() : "N/A") +
                ", curso=" + (curso != null ? curso.getNome() : "N/A") +
                ", estado=" + estadoFinal +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
