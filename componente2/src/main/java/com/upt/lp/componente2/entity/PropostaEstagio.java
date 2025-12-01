package com.upt.lp.componente2.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "proposta_estagio")
public class PropostaEstagio {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", length = 2000)
    private String descricao;

    @Column(name = "requisitos", length = 2000)
    private String requisitos;

    @Column(name = "beneficios", length = 2000)
    private String beneficios;

    @Column(name = "localizacao", length = 200)
    private String localizacao;

    @Column(name = "duracao_meses")
    private Integer duracaoMeses;

    @Column(name = "remunerado")
    private Boolean remunerado;

    @Column(name = "valor_remuneracao")
    private Double valorRemuneracao;

    @Column(name = "vagas_disponiveis")
    private Integer vagasDisponiveis;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "status", length = 20)
    private String status;

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
        this.id = UUID.randomUUID().toString();
        this.status = "PENDENTE";
        this.dataProposta = LocalDate.now();
        this.remunerado = false;
    }

    public PropostaEstagio(String titulo, String descricao, String requisitos, String localizacao, 
                          Integer duracaoMeses, Boolean remunerado, Integer vagasDisponiveis, 
                          String tipo, Empresa empresa, RepresentanteEmpresa representante) {
        this();
        validarProposta(titulo, descricao, duracaoMeses, vagasDisponiveis, tipo, empresa, representante);
        
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
    }

    private void validarProposta(String titulo, String descricao, Integer duracaoMeses,
                               Integer vagasDisponiveis, String tipo, Empresa empresa,
                               RepresentanteEmpresa representante) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
        if (duracaoMeses != null && duracaoMeses <= 0) {
            throw new IllegalArgumentException("A duração tem de ser maior que zero.");
        }
        if (vagasDisponiveis != null && vagasDisponiveis <= 0) {
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

    public Integer getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(Integer duracaoMeses) {
        if (duracaoMeses != null && duracaoMeses <= 0) {
            throw new IllegalArgumentException("A duração tem de ser maior que zero.");
        }
        this.duracaoMeses = duracaoMeses;
    }

    public Boolean getRemunerado() {
        return remunerado;
    }

    public void setRemunerado(Boolean remunerado) {
        this.remunerado = remunerado;
    }

    public Double getValorRemuneracao() {
        return valorRemuneracao;
    }

    public void setValorRemuneracao(Double valorRemuneracao) {
        this.valorRemuneracao = valorRemuneracao;
    }

    public Integer getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(Integer vagasDisponiveis) {
        if (vagasDisponiveis != null && vagasDisponiveis <= 0) {
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
        if (area != null) {
            this.areas.add(area);
        }
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
        return "PropostaEstagio{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", beneficios='" + beneficios + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", duracaoMeses=" + duracaoMeses +
                ", remunerado=" + remunerado +
                ", valorRemuneracao=" + valorRemuneracao +
                ", vagasDisponiveis=" + vagasDisponiveis +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                ", dataProposta=" + dataProposta +
                ", empresa=" + (empresa != null ? empresa.getNome() : "N/A") +
                ", representante=" + (representante != null ? representante.getNome() : "N/A") +
                '}';
    }
}
