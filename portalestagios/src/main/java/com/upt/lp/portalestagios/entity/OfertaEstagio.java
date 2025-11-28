package com.upt.lp.portalestagios.entity;

import com.upt.lp.portalestagios.enums.TipoEstagio;
import com.upt.lp.portalestagios.enums.StatusOferta;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 2000)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_responsavel_id")
    private Coordenador coordenadorResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private AreaEstagio area;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 30)
    private TipoEstagio tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private StatusOferta status = StatusOferta.PENDENTE;

    @Column(name = "numero_vagas")
    private int numeroVagas = 1;

    @CreationTimestamp
    @Column(name = "data_publicacao", updatable = false)
    private LocalDateTime dataPublicacao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "requisitos", length = 1000)
    private String requisitos;

    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidatura> candidaturas = new ArrayList<>();

    public OfertaEstagio() {}

    public OfertaEstagio(String titulo, String descricao, Empresa empresa, TipoEstagio tipo, int duracaoMeses) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.tipo = tipo;
        this.numeroVagas = Math.max(1, this.numeroVagas);
    }

    // ---------------------------
    // MÉTODOS NOVOS (CORRIGIDOS)
    // ---------------------------

    /** Aprova a oferta */
    public void aprovar() {
        this.status = StatusOferta.APROVADO;
    }

    /** Rejeita a oferta */
    public void rejeitar() {
        this.status = StatusOferta.REJEITADO;
    }

    /** Verifica se está disponível */
    public boolean estaDisponivel() {
        return status == StatusOferta.APROVADO && numeroVagas > 0;
    }

    // ---------------------------
    // Getters e setters
    // ---------------------------

    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Coordenador getCoordenadorResponsavel() { return coordenadorResponsavel; }
    public void setCoordenadorResponsavel(Coordenador coordenadorResponsavel) { this.coordenadorResponsavel = coordenadorResponsavel; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public AreaEstagio getArea() { return area; }
    public void setArea(AreaEstagio area) { this.area = area; }
    public TipoEstagio getTipo() { return tipo; }
    public void setTipo(TipoEstagio tipo) { this.tipo = tipo; }
    public StatusOferta getStatus() { return status; }
    public void setStatus(StatusOferta status) { this.status = status; }
    public int getNumeroVagas() { return numeroVagas; }
    public void setNumeroVagas(int numeroVagas) { this.numeroVagas = numeroVagas; }
    public LocalDateTime getDataPublicacao() { return dataPublicacao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getRequisitos() { return requisitos; }
    public void setRequisitos(String requisitos) { this.requisitos = requisitos; }

    public List<Candidatura> getCandidaturas() { return candidaturas; }

    public void adicionarCandidatura(Candidatura c) {
        if (!candidaturas.contains(c)) {
            candidaturas.add(c);
            c.setOferta(this);
        }
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfertaEstagio)) return false;
        OfertaEstagio that = (OfertaEstagio) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
