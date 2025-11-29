package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioResponseDTO;
import com.upt.lp.portalestagios.entity.*;
import com.upt.lp.portalestagios.enums.TipoEstagio;

public class OfertaEstagioMapper {

    public static OfertaEstagio toEntity(OfertaEstagioRequestDTO dto,
                                        Empresa empresa,
                                        Coordenador coord,
                                        Curso curso,
                                        AreaEstagio area) {

        OfertaEstagio o = new OfertaEstagio();
        o.setTitulo(dto.getTitulo());
        o.setDescricao(dto.getDescricao());
        o.setEmpresa(empresa);
        o.setCoordenadorResponsavel(coord);
        o.setCurso(curso);
        o.setArea(area);

        if (dto.getTipo() != null)
            o.setTipo(TipoEstagio.valueOf(dto.getTipo()));

        o.setNumeroVagas(dto.getNumeroVagas());
        o.setDataInicio(dto.getDataInicio());
        o.setDataFim(dto.getDataFim());
        o.setLocalizacao(dto.getLocalizacao());
        o.setRequisitos(dto.getRequisitos());

        return o;
    }

    public static OfertaEstagioResponseDTO toDTO(OfertaEstagio o) {
        OfertaEstagioResponseDTO dto = new OfertaEstagioResponseDTO();

        dto.setId(o.getId());
        dto.setTitulo(o.getTitulo());
        dto.setDescricao(o.getDescricao());

        dto.setEmpresaNome(o.getEmpresa() != null ? o.getEmpresa().getNome() : null);
        dto.setCursoNome(o.getCurso() != null ? o.getCurso().getNome() : null);
        dto.setAreaNome(o.getArea() != null ? o.getArea().getNome() : null);
        dto.setCoordenadorNome(o.getCoordenadorResponsavel() != null ?
                o.getCoordenadorResponsavel().getNome() : null);

        dto.setTipo(o.getTipo());
        dto.setStatus(o.getStatus());
        dto.setNumeroVagas(o.getNumeroVagas());

        dto.setDataPublicacao(o.getDataPublicacao());
        dto.setDataInicio(o.getDataInicio());
        dto.setDataFim(o.getDataFim());
        dto.setLocalizacao(o.getLocalizacao());
        dto.setRequisitos(o.getRequisitos());

        return dto;
    }
}

