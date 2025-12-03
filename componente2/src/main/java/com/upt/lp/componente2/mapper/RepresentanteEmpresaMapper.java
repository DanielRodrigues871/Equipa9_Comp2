package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.RepresentanteEmpresaDTO;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;

public final class RepresentanteEmpresaMapper {

    private RepresentanteEmpresaMapper() {
    }

    public static RepresentanteEmpresaDTO toDTO(RepresentanteEmpresa entity) {
        if (entity == null) {
            return null;
        }

        RepresentanteEmpresaDTO dto = new RepresentanteEmpresaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCargo(entity.getCargo());
        dto.setTelefone(entity.getTelefone());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataAtualizacao(entity.getDataAtualizacao());

        Empresa empresa = entity.getEmpresa();
        if (empresa != null) {
            dto.setEmpresaId(empresa.getId());
            dto.setEmpresaNome(empresa.getNome());
        }

        return dto;
    }

    public static RepresentanteEmpresa toEntity(RepresentanteEmpresaDTO dto) {
        if (dto == null) {
            return null;
        }

        RepresentanteEmpresa r = new RepresentanteEmpresa();
        r.setNome(dto.getNome());
        r.setEmail(dto.getEmail());
        r.setCargo(dto.getCargo());
        r.setTelefone(dto.getTelefone());
        // password continua a ser tratada noutro DTO ou campo se quiseres expor
        return r;
    }
}
