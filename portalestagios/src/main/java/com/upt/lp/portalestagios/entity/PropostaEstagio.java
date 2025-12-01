package com.upt.lp.portalestagios.entity;

import com.upt.lp.portalestagios.enums.StatusProposta;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "proposta_estagio")
public class PropostaEstagio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "area_id")
    private AreaEstagio area;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Coordenador coordenadorResponsavel;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusProposta status;

    @Column(name = "data_registo", nullable = false)
    private LocalDateTime dataRegisto;

    public PropostaEstagio() {
        this.status = StatusProposta.SUBMETIDA;
        this.dataRegisto = LocalDateTime.now();
    }

    public PropostaEstagio(AreaEstagio area, Empresa empresa, String titulo, String descricao) {
        this();
        this.area = area;
        this.empresa = empresa;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void aprovar(Coordenador c) {
        this.coordenadorResponsavel = c;
        this.status = StatusProposta.APROVADA;
    }

    public void rejeitar(Coordenador c) {
        this.coordenadorResponsavel = c;
        this.status = StatusProposta.REJEITADA;
    }

    public UUID getId() { return id; }

    public AreaEstagio getArea() { return area; }
    public Empresa getEmpresa() { return empresa; }
    public Coordenador getCoordenadorResponsavel() { return coordenadorResponsavel; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    
    public LocalDateTime getDataRegisto() { return dataRegisto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropostaEstagio)) return false;
        PropostaEstagio that = (PropostaEstagio) o;
        return Objects.equals(id, that.id);
    }
    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }


    @Override
    public int hashCode() { return Objects.hash(id); }
}
