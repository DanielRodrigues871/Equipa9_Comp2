package com.upt.lp.portalestagios.dto.estagio;

import java.time.LocalDateTime;
import java.util.UUID;

public class EstagioRequestDTO {

    private UUID estudanteId;
    private UUID propostaId;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public UUID getEstudanteId() { return estudanteId; }
    public void setEstudanteId(UUID estudanteId) { this.estudanteId = estudanteId; }

    public UUID getPropostaId() { return propostaId; }
    public void setPropostaId(UUID propostaId) { this.propostaId = propostaId; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }
}
