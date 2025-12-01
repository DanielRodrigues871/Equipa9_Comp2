package com.upt.lp.portalestagios.dto.proposta;

import java.util.UUID;

public class PropostaEstagioRequestDTO {

    private UUID areaId;
    private UUID empresaId;

    private String titulo;
    private String descricao;

    public UUID getAreaId() { return areaId; }
    public void setAreaId(UUID areaId) { this.areaId = areaId; }

    public UUID getEmpresaId() { return empresaId; }
    public void setEmpresaId(UUID empresaId) { this.empresaId = empresaId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

