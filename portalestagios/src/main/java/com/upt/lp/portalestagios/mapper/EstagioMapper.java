package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.estagio.EstagioRequestDTO;
import com.upt.lp.portalestagios.dto.estagio.EstagioResponseDTO;
import com.upt.lp.portalestagios.entity.*;

public class EstagioMapper {

    public static EstagioResponseDTO toDTO(Estagio e) {
        EstagioResponseDTO dto = new EstagioResponseDTO();

        dto.setId(e.getId());

        if (e.getEstudante() != null) {
            dto.setEstudanteId(e.getEstudante().getId());
            dto.setEstudanteNome(e.getEstudante().getNome());
        }

        if (e.getProposta() != null) {
            dto.setPropostaId(e.getProposta().getId());
        }

        dto.setDataInicio(e.getDataInicio());
        dto.setDataFim(e.getDataFim());
        dto.setDataCriacao(e.getDataCriacao());

        return dto;
    }
}
