package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.OfertaEstagioDTO;
import com.upt.lp.componente2.entity.*;

public final class OfertaEstagioMapper {

    private OfertaEstagioMapper() {
    }

    public static OfertaEstagioDTO toDTO(OfertaEstagio entity) {
        if (entity == null) {
            return null;
        }

        OfertaEstagioDTO dto = new OfertaEstagioDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setTipo(entity.getTipo());
        dto.setLocalizacao(entity.getLocalizacao());
        dto.setDuracaoMeses(entity.getDuracaoMeses());
        dto.setRequisitos(entity.getRequisitos());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        dto.setDataLimiteInscricao(entity.getDataLimiteInscricao());
        dto.setStatus(entity.getStatus());
        dto.setNumeroVagas(entity.getNumeroVagas());
        dto.setDataPublicacao(entity.getDataPublicacao());
        dto.setDataAprovacao(entity.getDataAprovacao());

        Empresa emp = entity.getEmpresa();
        if (emp != null) {
            dto.setEmpresaId(emp.getId());
            dto.setEmpresaNome(emp.getNome());
        }

        AreaEstagio area = entity.getArea();
        if (area != null) {
            dto.setAreaId(area.getId());
            dto.setAreaNome(area.getNome());
        }

        Curso curso = entity.getCurso();
        if (curso != null) {
            dto.setCursoId(curso.getId());
            dto.setCursoNome(curso.getNome());
        }

        Coordenador coord = entity.getCoordenadorResponsavel();
        if (coord != null) {
            dto.setCoordenadorResponsavelId(coord.getId());
            dto.setCoordenadorResponsavelNome(coord.getNome());
        }

        if (entity.getCandidaturas() != null) {
            dto.setNumeroCandidaturas(entity.getCandidaturas().size());
        }

        return dto;
    }

    public static OfertaEstagio toEntity(OfertaEstagioDTO dto) {
        if (dto == null) {
            return null;
        }

        OfertaEstagio o = new OfertaEstagio();
        o.setTitulo(dto.getTitulo());
        o.setDescricao(dto.getDescricao());
        o.setTipo(dto.getTipo());
        o.setLocalizacao(dto.getLocalizacao());
        o.setDuracaoMeses(dto.getDuracaoMeses());
        o.setRequisitos(dto.getRequisitos());
        o.setDataInicio(dto.getDataInicio());
        o.setDataFim(dto.getDataFim());
        o.setDataLimiteInscricao(dto.getDataLimiteInscricao());
        o.setNumeroVagas(dto.getNumeroVagas());
        // empresa, area, curso, coordenador são ligados no service
        return o;
    }
}