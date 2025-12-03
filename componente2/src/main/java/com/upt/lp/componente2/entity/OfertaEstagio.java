package com.upt.lp.componente2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upt.lp.componente2.enums.StatusOferta;
import com.upt.lp.componente2.enums.TipoEstagio;
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
    @JsonIgnore
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

    public OfertaEstagio(String titulo,
                         String descricao,
                         Empresa empresa,
                         TipoEstagio tipo,
                         int duracaoMeses) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.tipo = tipo;
        this.duracaoMeses = duracaoMeses;
    }

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
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public AreaEstagio getArea() {
        return area;
    }

    public void setArea(AreaEstagio area) {
        this.area = area;
    }

    public Coordenador getCoordenadorResponsavel() {
        return coordenadorResponsavel;
    }

    public void setCoordenadorResponsavel(Coordenador coordenadorResponsavel) {
        this.coordenadorResponsavel = coordenadorResponsavel;
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
                ", candidaturas=" + (candidaturas != null ? candidaturas.size() : 0) +
                '}';
    }
}