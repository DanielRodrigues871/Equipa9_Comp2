package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.NotificacaoDTO;
import com.upt.pt.api.entity.Notificacao;

import java.time.format.DateTimeFormatter;

public class NotificacaoMapper {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static NotificacaoDTO toDTO(Notificacao n) {
        NotificacaoDTO dto = new NotificacaoDTO();
        dto.setId(n.getId());
        dto.setMensagem(n.getMensagem());
        dto.setLida(n.isLida());
        dto.setData(n.getDataCriacao().format(FORMATTER));
        return dto;
    }
}
