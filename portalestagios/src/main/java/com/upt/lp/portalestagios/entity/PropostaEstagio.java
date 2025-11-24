package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "proposta_estagio")
public class PropostaEstagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "requisitos", length = 1000)
    private String requisitos;

    @Column(name = "beneficios", length = 1000)
    private String beneficios;

    @Column(name = "localizacao", length = 255)
    private String localizacao;

    @Column(name = "duracao_meses")
    private int duracaoMeses;

    @Column(name = "remunerado")
    private boolean remunerado;

    @Column(name = "valor_remuneracao")
    private double valorRemuneracao;

    @Column(name = "vagas_disponiveis")
    private int vagasDisponiveis;

    @Column(name = "tipo", length = 50)
    private String tipo; // Ideal usar enum, se possível

    @Column(name = "status", length = 50)
    private String status; // Ideal usar enum, se possível

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
        this.status = "PENDENTE";
        this.dataProposta = LocalDate.now();
    }

    public PropostaEstagio(String titulo, String descricao, String requisitos, String localizacao, int duracaoMeses,
                           boolean remunerado, int vagasDisponiveis, String tipo, Empresa empresa, RepresentanteEmpresa representante) {
        this();
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

    // Getters e Setters

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
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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
        this.duracaoMeses = duracaoMeses;
    }

    public boolean isRemunerado() {
        return remunerado;
    }

    public void setRemunerado(boolean remunerado) {
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
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
        this.empresa = empresa;
    }

    public RepresentanteEmpresa getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteEmpresa representante) {
        this.representante = representante;
    }

    public List<AreaEstagio> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaEstagio> areas) {
        this.areas = areas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropostaEstagio)) return false;
        PropostaEstagio that = (PropostaEstagio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PropostaEstagio{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", empresa=" + (empresa != null ? empresa.getNome() : "N/A") +
                ", representante=" + (representante != null ? representante.getNome() : "N/A") +
                ", status='" + status + '\'' +
                '}';
    }
}

