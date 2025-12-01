package com.upt.lp.portalestagios.dto.estagio;

import java.time.LocalDateTime;
import java.util.UUID;

public class EstagioResponseDTO {

    private UUID id;

    private UUID estudanteId;
    private String estudanteNome;

    private UUID propostaId;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataCriacao;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEstudanteId() { return estudanteId; }
    public void setEstudanteId(UUID estudanteId) { this.estudanteId = estudanteId; }

    public String getEstudanteNome() { return estudanteNome; }
    public void setEstudanteNome(String estudanteNome) { this.estudanteNome = estudanteNome; }

    public UUID getPropostaId() { return propostaId; }
    public void setPropostaId(UUID propostaId) { this.propostaId = propostaId; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}

