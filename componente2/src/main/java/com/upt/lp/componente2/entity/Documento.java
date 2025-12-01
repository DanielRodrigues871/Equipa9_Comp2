package com.upt.lp.componente2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nome_empresa", nullable = false, length = 200)
    private String nomeEmpresa;

    @Column(name = "contacto_empresa", nullable = false, length = 20)
    private String contactoEmpresa;

    @Column(name = "objetivo_estagio", nullable = false, length = 1000)
    private String objetivoEstagio;

    @Column(name = "data_upload", nullable = false)
    private LocalDateTime dataUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    public Documento() {
        this.id = UUID.randomUUID().toString();
        this.dataUpload = LocalDateTime.now();
    }

    public Documento(String nomeEmpresa,
                     String contactoEmpresa,
                     String objetivoEstagio,
                     Estudante estudante) {
        this();
        validarDocumento(nomeEmpresa, contactoEmpresa, objetivoEstagio, estudante);
        
        this.nomeEmpresa = nomeEmpresa;
        this.contactoEmpresa = contactoEmpresa;
        this.objetivoEstagio = objetivoEstagio;
        this.estudante = estudante;
    }

    private void validarDocumento(String nomeEmpresa, String contactoEmpresa, 
                                 String objetivoEstagio, Estudante estudante) {
        if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
            throw new IllegalArgumentException("Nome da empresa é obrigatório.");
        }
        if (contactoEmpresa == null || contactoEmpresa.isBlank()) {
            throw new IllegalArgumentException("Contacto da empresa é obrigatório.");
        }
        if (objetivoEstagio == null || objetivoEstagio.isBlank()) {
            throw new IllegalArgumentException("Objetivo do estágio é obrigatório.");
        }
        if (estudante == null) {
            throw new IllegalArgumentException("Estudante é obrigatório.");
        }
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        if (nomeEmpresa == null || nomeEmpresa.isBlank()) {
            throw new IllegalArgumentException("Nome da empresa é obrigatório.");
        }
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getContactoEmpresa() {
        return contactoEmpresa;
    }

    public void setContactoEmpresa(String contactoEmpresa) {
        if (contactoEmpresa == null || contactoEmpresa.isBlank()) {
            throw new IllegalArgumentException("Contacto da empresa é obrigatório.");
        }
        this.contactoEmpresa = contactoEmpresa;
    }

    public String getObjetivoEstagio() {
        return objetivoEstagio;
    }

    public void setObjetivoEstagio(String objetivoEstagio) {
        if (objetivoEstagio == null || objetivoEstagio.isBlank()) {
            throw new IllegalArgumentException("Objetivo do estágio é obrigatório.");
        }
        this.objetivoEstagio = objetivoEstagio;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        if (estudante == null) {
            throw new IllegalArgumentException("Estudante é obrigatório.");
        }
        this.estudante = estudante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id='" + id + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", contactoEmpresa='" + contactoEmpresa + '\'' +
                ", objetivoEstagio='" + objetivoEstagio + '\'' +
                ", dataUpload=" + dataUpload +
                '}';
    }
}