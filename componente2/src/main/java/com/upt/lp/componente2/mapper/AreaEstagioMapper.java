package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.AreaEstagioDTO;
import com.upt.lp.componente2.entity.AreaEstagio;

public final class AreaEstagioMapper {

    private AreaEstagioMapper() {
    }

    public static AreaEstagioDTO toDTO(AreaEstagio entity) {
        if (entity == null) {
            return null;
        }

        AreaEstagioDTO dto = new AreaEstagioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    public static AreaEstagio toEntity(AreaEstagioDTO dto) {
        if (dto == null) {
            return null;
        }

        AreaEstagio a = new AreaEstagio();
        a.setNome(dto.getNome());
        a.setDescricao(dto.getDescricao());
        return a;
    }
}
