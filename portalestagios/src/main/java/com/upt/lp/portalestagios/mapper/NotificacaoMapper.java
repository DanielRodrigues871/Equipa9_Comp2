package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.notificacao.NotificacaoResponseDTO;
import com.upt.lp.portalestagios.entity.Notificacao;

public class NotificacaoMapper {

    public static NotificacaoResponseDTO toDTO(Notificacao n) {
        NotificacaoResponseDTO dto = new NotificacaoResponseDTO();

        dto.setId(n.getId());
        dto.setUtilizadorId(n.getUtilizador().getId());
        dto.setMensagem(n.getMensagem());
        dto.setLida(n.isLida());
        dto.setDataCriacao(n.getDataCriacao());

        return dto;
    }
}
