package com.upt.lp.portalestagios.dto.proposta;

import com.upt.lp.portalestagios.enums.StatusProposta;

import java.time.LocalDateTime;
import java.util.UUID;

public class PropostaEstagioResponseDTO {

    private UUID id;

    private UUID areaId;
    private String areaNome;

    private UUID empresaId;
    private String empresaNome;

    private UUID coordenadorId;
    private String coordenadorNome;

    private String titulo;
    private String descricao;

    private StatusProposta status;
    private LocalDateTime dataRegisto;

    public PropostaEstagioResponseDTO() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAreaId() {
        return areaId;
    }

    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public String getAreaNome() {
        return areaNome;
    }

    public void setAreaNome(String areaNome) {
        this.areaNome = areaNome;
    }

    public UUID getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(UUID empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public UUID getCoordenadorId() {
        return coordenadorId;
    }

    public void setCoordenadorId(UUID coordenadorId) {
        this.coordenadorId = coordenadorId;
    }

    public String getCoordenadorNome() {
        return coordenadorNome;
    }

    public void setCoordenadorNome(String coordenadorNome) {
        this.coordenadorNome = coordenadorNome;
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

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public LocalDateTime getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(LocalDateTime dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
}
