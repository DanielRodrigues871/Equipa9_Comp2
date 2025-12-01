package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.CursoDTO;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Curso;
import com.upt.pt.api.entity.Departamento;

public final class CursoMapper {

    private CursoMapper() {
    }

    public static CursoDTO toDTO(Curso entity) {
        if (entity == null) {
            return null;
        }

        CursoDTO dto = new CursoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setDuracaoAnos(entity.getDuracaoAnos());
        dto.setGrau(entity.getGrau());

        Departamento dep = entity.getDepartamento();
        if (dep != null) {
            dto.setDepartamentoId(dep.getId());
            dto.setDepartamentoNome(dep.getNome());
        }

        Coordenador coord = entity.getCoordenador();
        if (coord != null) {
            dto.setCoordenadorId(coord.getId());
            dto.setCoordenadorNome(coord.getNome());
        }

        return dto;
    }

    public static Curso toEntity(CursoDTO dto) {
        if (dto == null) {
            return null;
        }

        Curso c = new Curso();
        c.setNome(dto.getNome());
        c.setCodigo(dto.getCodigo());
        c.setDuracaoAnos(dto.getDuracaoAnos());
        c.setGrau(dto.getGrau());
        // departamento e coordenador são ligados no service
        return c;
    }
}
