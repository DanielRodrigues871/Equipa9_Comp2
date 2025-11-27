package com.upt.lp.portalestagios.entity;

import com.upt.lp.portalestagios.enums.StatusCandidatura;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador_responsavel_id")
    private Coordenador coordenadorResponsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaEstagio oferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCandidatura status;

    @Column(name = "carta_motivacao", length = 2000)
    private String cartaMotivacao;

    @Column(name = "data_submissao", nullable = false)
    private LocalDateTime dataSubmissao;

    @Column(name = "data_analise")
    private LocalDateTime dataAnalise;

    @Column(name = "observacoes", length = 2000)
    private String observacoes;

    public Candidatura() {
        this.status = StatusCandidatura.SUBMETIDA;
        this.dataSubmissao = LocalDateTime.now();
    }

    public Candidatura(Estudante estudante, OfertaEstagio oferta, String cartaMotivacao) {
        this();
        this.estudante = estudante;
        this.oferta = oferta;
        this.cartaMotivacao = cartaMotivacao;
    }

    public UUID getId() { return id; }
    public Estudante getEstudante() { return estudante; }
    public void setEstudante(Estudante estudante) { this.estudante = estudante; }
    public Coordenador getCoordenadorResponsavel() { return coordenadorResponsavel; }
    public void setCoordenadorResponsavel(Coordenador coordenadorResponsavel) { this.coordenadorResponsavel = coordenadorResponsavel; }
    public OfertaEstagio getOferta() { return oferta; }
    public void setOferta(OfertaEstagio oferta) { this.oferta = oferta; }
    public StatusCandidatura getStatus() { return status; }
    public void setStatus(StatusCandidatura status) { this.status = status; }
    public String getCartaMotivacao() { return cartaMotivacao; }
    public void setCartaMotivacao(String cartaMotivacao) { this.cartaMotivacao = cartaMotivacao; }
    public LocalDateTime getDataSubmissao() { return dataSubmissao; }
    public LocalDateTime getDataAnalise() { return dataAnalise; }
    public String getObservacoes() { return observacoes; }

    public void colocarEmAnalise() {
        this.status = StatusCandidatura.EM_ANALISE;
        this.dataAnalise = LocalDateTime.now();
    }
    public void aprovar() {
        this.status = StatusCandidatura.APROVADA;
        this.dataAnalise = LocalDateTime.now();
    }
    public void rejeitar(String obs) {
        this.status = StatusCandidatura.REJEITADA;
        this.dataAnalise = LocalDateTime.now();
        this.observacoes = obs;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidatura)) return false;
        Candidatura that = (Candidatura) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
