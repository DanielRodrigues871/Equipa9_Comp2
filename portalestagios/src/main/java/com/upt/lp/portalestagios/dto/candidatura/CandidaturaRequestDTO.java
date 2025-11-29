package com.upt.lp.portalestagios.dto.candidatura;

import java.util.UUID;

public class CandidaturaRequestDTO {
    private UUID estudanteId;
    private UUID ofertaId;
    private String cartaMotivacao;
    private String observacoes;

    // getters / setters
    public UUID getEstudanteId() { return estudanteId; }
    public void setEstudanteId(UUID estudanteId) { this.estudanteId = estudanteId; }

    public UUID getOfertaId() { return ofertaId; }
    public void setOfertaId(UUID ofertaId) { this.ofertaId = ofertaId; }

    public String getCartaMotivacao() { return cartaMotivacao; }
    public void setCartaMotivacao(String cartaMotivacao) { this.cartaMotivacao = cartaMotivacao; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
