package com.upt.pt.api.entity;

import com.upt.pt.api.enums.StatusCandidatura;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "candidatura")
public class Candidatura {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coordenador_responsavel_id")
    private Coordenador coordenadorResponsavel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaEstagio oferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCandidatura status;

    @Column(name = "carta_motivacao")
    private String cartaMotivacao;

    @Column(name = "data_submissao", nullable = false)
    private LocalDateTime dataSubmissao;

    @Column(name = "data_analise")
    private LocalDateTime dataAnalise;

    @Column(name = "observacoes")
    private String observacoes;

    public Candidatura() {
        this.id = UUID.randomUUID().toString();
        this.status = StatusCandidatura.SUBMETIDA;
        this.dataSubmissao = LocalDateTime.now();
    }

    public Candidatura(Estudante estudante,
                       OfertaEstagio oferta,
                       String cartaMotivacao) {
        this();
        this.estudante = estudante;
        this.oferta = oferta;
        this.cartaMotivacao = cartaMotivacao;
    }

    public void colocarEmAnalise() {
        this.status = StatusCandidatura.EM_ANALISE;
        this.dataAnalise = LocalDateTime.now();
    }

    public void aprovar() {
        this.status = StatusCandidatura.APROVADA;
        this.dataAnalise = LocalDateTime.now();
    }

    public void rejeitar(String observacoes) {
        this.status = StatusCandidatura.REJEITADA;
        this.dataAnalise = LocalDateTime.now();
        this.observacoes = observacoes;
    }

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
        this.estudante = estudante;
    }

    public Coordenador getCoordenadorResponsavel() {
        return coordenadorResponsavel;
    }

    public void setCoordenadorResponsavel(Coordenador coordenadorResponsavel) {
        this.coordenadorResponsavel = coordenadorResponsavel;
    }

    public OfertaEstagio getOferta() {
        return oferta;
    }

    public void setOferta(OfertaEstagio oferta) {
        this.oferta = oferta;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }

    public String getCartaMotivacao() {
        return cartaMotivacao;
    }

    public void setCartaMotivacao(String cartaMotivacao) {
        this.cartaMotivacao = cartaMotivacao;
    }

    public LocalDateTime getDataSubmissao() {
        return dataSubmissao;
    }

    public void setDataSubmissao(LocalDateTime dataSubmissao) {
        this.dataSubmissao = dataSubmissao;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidatura)) return false;
        Candidatura that = (Candidatura) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidatura{" +
                "id='" + id + '\'' +
                ", estudante=" + (estudante != null ? estudante.getNome() : "N/A") +
                ", oferta=" + (oferta != null ? oferta.getTitulo() : "N/A") +
                ", status=" + status +
                ", dataSubmissao=" + dataSubmissao +
                '}';
    }
}
