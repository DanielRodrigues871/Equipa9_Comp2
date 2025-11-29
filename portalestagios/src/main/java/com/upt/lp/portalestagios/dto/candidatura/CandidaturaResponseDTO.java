package com.upt.lp.portalestagios.dto.candidatura;

import com.upt.lp.portalestagios.enums.StatusCandidatura;
import java.time.LocalDateTime;
import java.util.UUID;

public class CandidaturaResponseDTO {

    private UUID id;
    private UUID estudanteId;
    private String estudanteNome;

    private UUID ofertaId;
    private String ofertaTitulo;

    private StatusCandidatura status;
    private String cartaMotivacao;
    private String observacoes;

    private LocalDateTime dataSubmissao;
    private LocalDateTime dataAnalise;

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEstudanteId() { return estudanteId; }
    public void setEstudanteId(UUID estudanteId) { this.estudanteId = estudanteId; }

    public String getEstudanteNome() { return estudanteNome; }
    public void setEstudanteNome(String estudanteNome) { this.estudanteNome = estudanteNome; }

    public UUID getOfertaId() { return ofertaId; }
    public void setOfertaId(UUID ofertaId) { this.ofertaId = ofertaId; }

    public String getOfertaTitulo() { return ofertaTitulo; }
    public void setOfertaTitulo(String ofertaTitulo) { this.ofertaTitulo = ofertaTitulo; }

    public StatusCandidatura getStatus() { return status; }
    public void setStatus(StatusCandidatura status) { this.status = status; }

    public String getCartaMotivacao() { return cartaMotivacao; }
    public void setCartaMotivacao(String cartaMotivacao) { this.cartaMotivacao = cartaMotivacao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public java.time.LocalDateTime getDataSubmissao() { return dataSubmissao; }
    public void setDataSubmissao(java.time.LocalDateTime dataSubmissao) { this.dataSubmissao = dataSubmissao; }

    public java.time.LocalDateTime getDataAnalise() { return dataAnalise; }
    public void setDataAnalise(java.time.LocalDateTime dataAnalise) { this.dataAnalise = dataAnalise; }
}
