package com.upt.lp.portalestagios.entity;

import com.upt.lp.portalestagios.enums.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, updatable = false, nullable = false)
    private String id;

    @Column(name = "titulo", nullable = false, length = 200)
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
    @Column(name = "tipo", length = 50)
    private TipoEstagio tipo;

    @Column(name = "localizacao", length = 255)
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
    @Column(name = "status", length = 50)
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
        this.status = StatusOferta.PENDENTE;
        this.dataPublicacao = LocalDateTime.now();
    }

    public OfertaEstagio(String titulo, String descricao, Empresa empresa, TipoEstagio tipo, int duracaoMeses) {
        this();
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
        if (this.status == StatusOferta.APROVADO) {
            this.candidaturas.add(candidatura);
            candidatura.setOferta(this);
        } else {
            throw new IllegalStateException("Não é possível candidatar-se a uma oferta não aprovada");
        }
    }

    public boolean estaDisponivel() {
        var hoje = LocalDate.now();
        if (dataInicio == null && dataFim == null) {
            return true;
        }
        if (dataInicio == null) {
            return !hoje.isAfter(dataFim);
        }
        if (dataFim == null) {
            return !hoje.isBefore(dataInicio);
        }
        return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataFim);
    }

    // Getters e Setters

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

    public Empresa getEmpresa

