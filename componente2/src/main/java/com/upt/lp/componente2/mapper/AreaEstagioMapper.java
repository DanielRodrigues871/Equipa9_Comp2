package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.AreaEstagioDTO;
import com.upt.lp.componente2.entity.AreaEstagio;

public class AreaEstagioMapper {
    
    public static AreaEstagioDTO toDTO(AreaEstagio area) {
        if (area == null) return null;
        
        return new AreaEstagioDTO(
            area.getId(),
            area.getNome(),
            area.getDescricao()
        );
    }
    
    public static AreaEstagio toEntity(AreaEstagioDTO dto) {
        if (dto == null) return null;
        
        AreaEstagio area = new AreaEstagio();
        area.setId(dto.getId());
        area.setNome(dto.getNome());
        area.setDescricao(dto.getDescricao());
        
        return area;
    }
}
