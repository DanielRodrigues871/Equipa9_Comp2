package com.upt.lp.portalestagios.dto.notificacao;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificacaoResponseDTO {

    private UUID id;
    private UUID utilizadorId;
    private String mensagem;
    private boolean lida;
    private LocalDateTime dataCriacao;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(UUID utilizadorId) { this.utilizadorId = utilizadorId; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}

