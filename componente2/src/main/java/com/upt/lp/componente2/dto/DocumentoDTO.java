package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;

public class DocumentoDTO {

    private String id;
    private String nomeEmpresa;
    private String contactoEmpresa;
    private String objetivoEstagio;
    private LocalDateTime dataUpload;

    private String estudanteId;
    private String estudanteNome;

    public DocumentoDTO() {
    }

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
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getContactoEmpresa() {
        return contactoEmpresa;
    }

    public void setContactoEmpresa(String contactoEmpresa) {
        this.contactoEmpresa = contactoEmpresa;
    }

    public String getObjetivoEstagio() {
        return objetivoEstagio;
    }

    public void setObjetivoEstagio(String objetivoEstagio) {
        this.objetivoEstagio = objetivoEstagio;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public String getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(String estudanteId) {
        this.estudanteId = estudanteId;
    }

    public String getEstudanteNome() {
        return estudanteNome;
    }

    public void setEstudanteNome(String estudanteNome) {
        this.estudanteNome = estudanteNome;
    }
}