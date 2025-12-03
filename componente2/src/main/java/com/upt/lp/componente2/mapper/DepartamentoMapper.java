package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.DepartamentoDTO;
import com.upt.lp.componente2.entity.Departamento;

public final class DepartamentoMapper {

    private DepartamentoMapper() {
    }

    public static DepartamentoDTO toDTO(Departamento entity) {
        if (entity == null) {
            return null;
        }

        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setDescricao(entity.getDescricao());

        if (entity.getCursos() != null) {
            dto.setNumeroCursos(entity.getCursos().size());
        }
        if (entity.getCoordenadores() != null) {
            dto.setNumeroCoordenadores(entity.getCoordenadores().size());
        }

        return dto;
    }

    public static Departamento toEntity(DepartamentoDTO dto) {
        if (dto == null) {
            return null;
        }

        Departamento d = new Departamento();
        d.setNome(dto.getNome());
        d.setCodigo(dto.getCodigo());
        d.setDescricao(dto.getDescricao());
        return d;
    }
}
