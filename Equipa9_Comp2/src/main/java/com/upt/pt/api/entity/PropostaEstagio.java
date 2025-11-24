package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proposta_estagio")
public class PropostaEstagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "requisitos", length = 1000)
    private String requisitos;

    @Column(name = "beneficios", length = 1000)
    private String beneficios;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "duracao_meses")
    private int duracaoMeses;

    @Column(name = "remunerado")
    private boolean remunerado;

    @Column(name = "valor_remuneracao")
    private double valorRemuneracao;

    @Column(name = "vagas_disponiveis")
    private int vagasDisponiveis;

    @Column(name = "tipo")
    private String tipo; // "CURRICULAR", "EXTRA_CURRICULAR"

    @Column(name = "status")
    private String status; // "PENDENTE", "APROVADA", "REJEITADA", "EXPIRADA"

    @Column(name = "data_proposta")
    private LocalDate dataProposta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representante_id")
    private RepresentanteEmpresa representante;

    @ManyToMany
    @JoinTable(
        name = "proposta_area",
        joinColumns = @JoinColumn(name = "proposta_id"),
        inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<AreaEstagio> areas = new ArrayList<>();

    public PropostaEstagio() {
    }

    public PropostaEstagio(String titulo, String descricao, String requisitos, String localizacao, int duracaoMeses,
                           boolean remunerado, int vagasDisponiveis, String tipo, Empresa empresa, RepresentanteEmpresa representante) {
    	if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
    	}
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
        if (duracaoMeses <= 0) {
            throw new IllegalArgumentException("A duração tem de ser maior que zero.");
        }
        if (vagasDisponiveis <= 0) {
            throw new IllegalArgumentException("O número de vagas tem de ser maior que zero.");
        }
        if (tipo == null || (!tipo.equals("CURRICULAR") && !tipo.equals("EXTRA_CURRICULAR"))) {
            throw new IllegalArgumentException("O tipo deve ser 'CURRICULAR' ou 'EXTRA_CURRICULAR'.");
        }
        if (empresa == null) {
            throw new IllegalArgumentException("A empresa é obrigatória.");
        }
        if (representante == null) {
            throw new IllegalArgumentException("O representante da empresa é obrigatório.");
        }
        if (remunerado && valorRemuneracao <= 0) {
            throw new IllegalArgumentException("Se o estágio for remunerado, o valor deve ser positivo.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.localizacao = localizacao;
        this.duracaoMeses = duracaoMeses;
        this.remunerado = remunerado;
        this.vagasDisponiveis = vagasDisponiveis;
        this.tipo = tipo;
        this.empresa = empresa;
        this.representante = representante;
        this.status = "PENDENTE";
        this.dataProposta = LocalDate.now();
    }

    // Getters e setters 

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
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
            throw new IllegalArgumentException("A duração tem de ser maior que zero.");
        }
		this.duracaoMeses = duracaoMeses;
	}

	public boolean isRemunerado() {
		return remunerado;
	}

	public void setRemunerado(boolean remunerado) {
		if (remunerado && valorRemuneracao <= 0) {
            throw new IllegalArgumentException("Se o estágio for remunerado, o valor deve ser positivo.");
        }
		this.remunerado = remunerado;
	}

	public double getValorRemuneracao() {
		return valorRemuneracao;
	}

	public void setValorRemuneracao(double valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}

	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(int vagasDisponiveis) {
		if (vagasDisponiveis <= 0) {
            throw new IllegalArgumentException("O número de vagas tem de ser maior que zero.");
        }
		this.vagasDisponiveis = vagasDisponiveis;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (tipo == null || (!tipo.equals("CURRICULAR") && !tipo.equals("EXTRA_CURRICULAR"))) {
            throw new IllegalArgumentException("O tipo deve ser 'CURRICULAR' ou 'EXTRA_CURRICULAR'.");
        }
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataProposta() {
		return dataProposta;
	}

	public void setDataProposta(LocalDate dataProposta) {
		this.dataProposta = dataProposta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		if (empresa == null) {
            throw new IllegalArgumentException("A empresa é obrigatória.");
        }
		this.empresa = empresa;
	}

	public RepresentanteEmpresa getRepresentante() {
		return representante;
	}

	public void setRepresentante(RepresentanteEmpresa representante) {
		if (representante == null) {
            throw new IllegalArgumentException("O representante da empresa é obrigatório.");
        }
		this.representante = representante;
	}

	public List<AreaEstagio> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaEstagio> areas) {
		this.areas = areas;
	}

	public void adicionarArea(AreaEstagio area) {
        this.areas.add(area);
    }

    public void aprovar() {
        this.status = "APROVADA";
    }

    public void rejeitar() {
        this.status = "REJEITADA";
    }

    public boolean isPendente() {
        return "PENDENTE".equals(this.status);
    }

    public boolean isAprovada() {
        return "APROVADA".equals(this.status);
    }

    @Override
    public String toString() {
        return "PropostaEstagio [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", requisitos=" + requisitos
                + ", beneficios=" + beneficios + ", localizacao=" + localizacao + ", duracaoMeses=" + duracaoMeses
                + ", remunerado=" + remunerado + ", valorRemuneracao=" + valorRemuneracao + ", vagasDisponiveis="
                + vagasDisponiveis + ", tipo=" + tipo + ", status=" + status + ", dataProposta=" + dataProposta
                + ", empresa=" + (empresa != null ? empresa.getNome() : "N/A")
                + ", representante=" + (representante != null ? representante.getNome() : "N/A") + "]";
    }
}
