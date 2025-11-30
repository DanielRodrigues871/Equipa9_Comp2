package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.area.AreaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.area.AreaEstagioResponseDTO;
import com.upt.lp.portalestagios.entity.AreaEstagio;

public class AreaEstagioMapper {

    public static AreaEstagio toEntity(AreaEstagioRequestDTO dto) {
        AreaEstagio a = new AreaEstagio();
        a.setNome(dto.getNome());
        a.setDescricao(dto.getDescricao());
        return a;
    }

   
        public static AreaEstagioResponseDTO toDTO(AreaEstagio a) {
            return new AreaEstagioResponseDTO(
                    a.getId(),
                    a.getNome(),
                    a.getDescricao()
            );
        }
    

}
