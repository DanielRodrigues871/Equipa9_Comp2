package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.NotificacaoDTO;
import com.upt.pt.api.entity.Notificacao;

/**
 * Converte entidade Notificacao em NotificacaoDTO.
 */
public class NotificacaoMapper {

    public static NotificacaoDTO toDTO(Notificacao n) {
        if (n == null) return null;

        NotificacaoDTO dto = new NotificacaoDTO();
        dto.setId(n.getId());
        dto.setTitulo(n.getTitulo());
        dto.setMensagem(n.getMensagem());
        dto.setLida(n.isLida());
        dto.setDataCriacao(n.getDataCriacao());
        dto.setUtilizadorId(n.getUtilizadorId());

        return dto;
    }
}