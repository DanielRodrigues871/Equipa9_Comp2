package com.upt.lp.portalestagios.dto.notificacao;

import java.util.UUID;

public class NotificacaoRequestDTO {

    private UUID utilizadorId;
    private String mensagem;

    public UUID getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(UUID utilizadorId) { this.utilizadorId = utilizadorId; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
