package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estagio_id", nullable = false)
    private Estagio estagio;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "ficheiro", nullable = false, length = 255)
    private String ficheiro;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    public Documento() {
        this.dataUpload = LocalDateTime.now();
    }

    public Documento(Estagio estagio, String tipo, String ficheiro) {
        this();
        this.estagio = estagio;
        this.tipo = tipo;
        this.ficheiro = ficheiro;
    }

    public UUID getId() { return id; }
    public Estagio getEstagio() { return estagio; }
    public void setEstagio(Estagio estagio) { this.estagio = estagio; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getFicheiro() { return ficheiro; }
    public void setFicheiro(String ficheiro) { this.ficheiro = ficheiro; }
    public LocalDateTime getDataUpload() { return dataUpload; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
