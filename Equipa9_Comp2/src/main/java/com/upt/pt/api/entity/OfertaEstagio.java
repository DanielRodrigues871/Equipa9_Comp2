package com.upt.pt.api.entity;

import com.upt.pt.api.enums.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "oferta_estagio")
public class OfertaEstagio {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private AreaEstagio area;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coordenador_responsavel_id")
    private Coordenador coordenadorResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoEstagio tipo;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "duracao_meses")
    private int duracaoMeses;

    @Column(name = "requisitos", length = 1000)
    private String requisitos;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_limite_inscricao")
    private LocalDate dataLimiteInscricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOferta status;

    @Column(name = "numero_vagas")
    private int numeroVagas;

    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Candidatura> candidaturas = new ArrayList<>();

    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    @Column(name = "data_aprovacao")
    private LocalDateTime dataAprovacao;

    public OfertaEstagio() {
        this.id = UUID.randomUUID().toString();
        this.status = StatusOferta.PENDENTE;
        this.dataPublicacao = LocalDateTime.now();
    }

    public OfertaEstagio(String titulo, String descricao, Empresa empresa, TipoEstagio tipo, int duracaoMeses) {
        this();
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa é obrigatória.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de estágio é obrigatório.");
        }
        if (duracaoMeses <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que 0 meses.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.tipo = tipo;
        this.duracaoMeses = duracaoMeses;
    }

    public void aprovar() {
        this.status = StatusOferta.APROVADO;
        this.dataAprovacao = LocalDateTime.now();
    }

    public void rejeitar() {
        this.status = StatusOferta.REJEITADO;
    }

    public void encerrar() {
        this.status = StatusOferta.ENCERRADO;
    }

    public void adicionarCandidatura(Candidatura candidatura) {
        LocalDate hoje = LocalDate.now();

        // Regra: Não permite candidatura se status não for aprovado
        if (this.status != StatusOferta.APROVADO) {
            throw new IllegalStateException("Não é possível candidatar-se a uma oferta não aprovada");
        }

        // NOVA regra: Não permite candidatura após data limite de inscrição, caso exista a data
        if (this.dataLimiteInscricao != null && hoje.isAfter(this.dataLimiteInscricao)) {
            throw new IllegalStateException("Prazo para candidatura expirou!");
        }

        this.candidaturas.add(candidatura);
        candidatura.setOferta(this);
    }


    public boolean estaDisponivel() {
        try {
            LocalDate hoje = LocalDate.now();
            
            // Se ambas as datas forem nulas, considerar como disponível?
            if (dataInicio == null && dataFim == null) {
                return true; // ou false conforme sua regra
            }
            
            // Se apenas dataInicio for nula
            if (dataInicio == null) {
                return !hoje.isAfter(dataFim);
            }
            
            // Se apenas dataFim for nula
            if (dataFim == null) {
                return !hoje.isBefore(dataInicio);
            }
            
            // Ambas as datas estão preenchidas
            return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataFim);
            
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Em caso de erro, considerar não disponível
        }
    }

    // Getters e setters

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		if (empresa == null) {
            throw new IllegalArgumentException("Empresa é obrigatória.");
        }
		this.empresa = empresa;
	}

	public AreaEstagio getArea() {
		return area;
	}

	public void setArea(AreaEstagio area) {
		this.area = area;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public TipoEstagio getTipo() {
		return tipo;
	}

	public void setTipo(TipoEstagio tipo) {
		if (tipo == null) {
            throw new IllegalArgumentException("Tipo de estágio é obrigatório.");
        }
		this.tipo = tipo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getDuracaoMeses() {
		return duracaoMeses;
	}

	public void setDuracaoMeses(int duracaoMeses) {
		if (duracaoMeses <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que 0 meses.");
        }
		this.duracaoMeses = duracaoMeses;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDate getDataLimiteInscricao() {
		return dataLimiteInscricao;
	}

	public void setDataLimiteInscricao(LocalDate dataLimiteInscricao) {
		this.dataLimiteInscricao = dataLimiteInscricao;
	}

	public StatusOferta getStatus() {
		return status;
	}

	public void setStatus(StatusOferta status) {
		this.status = status;
	}

	public int getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(int numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public LocalDateTime getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(LocalDateTime dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	
	public Coordenador getCoordenadorResponsavel() {
	    return coordenadorResponsavel;
	}

	public void setCoordenadorResponsavel(Coordenador coordenador) {
	    this.coordenadorResponsavel = coordenador;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfertaEstagio)) return false;
        OfertaEstagio that = (OfertaEstagio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OfertaEstagio{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", empresa=" + (empresa != null ? empresa.getNome() : "N/A") +
                ", tipo=" + tipo +
                ", status=" + status +
                ", candidaturas=" + candidaturas.size() +
                '}';
    }
}
