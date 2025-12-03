package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.CoordenadorDTO;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Departamento;

public final class CoordenadorMapper {

    private CoordenadorMapper() {
    }

    public static CoordenadorDTO toDTO(Coordenador entity) {
        if (entity == null) {
            return null;
        }

        CoordenadorDTO dto = new CoordenadorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataAtualizacao(entity.getDataAtualizacao());

        Departamento dep = entity.getDepartamento();
        if (dep != null) {
            dto.setDepartamentoId(dep.getId());
            dto.setDepartamentoNome(dep.getNome());
        }

        return dto;
    }

    public static Coordenador toEntity(CoordenadorDTO dto) {
        if (dto == null) {
            return null;
        }

        Coordenador c = new Coordenador();
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        // password virá noutro DTO ou campo específico se quiseres expor
        return c;
    }
}