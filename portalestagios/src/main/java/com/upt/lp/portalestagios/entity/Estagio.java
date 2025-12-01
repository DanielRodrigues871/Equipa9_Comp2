package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "estagio")
public class Estagio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proposta_id")
    private PropostaEstagio proposta;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    
    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    @OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

    public Estagio() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Estagio(Estudante estudante, PropostaEstagio proposta) {
        this();
        this.estudante = estudante;
        this.proposta = proposta;
    }

    public void adicionarDocumento(Documento documento) {
        documento.setEstagio(this);
        this.documentos.add(documento);
    }

    public UUID getId() { return id; }
    public Estudante getEstudante() { return estudante; }
    public PropostaEstagio getProposta() { return proposta; }
    
    public List<Documento> getDocumentos() { return documentos; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estagio)) return false;
        Estagio estagio = (Estagio) o;
        return Objects.equals(id, estagio.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
