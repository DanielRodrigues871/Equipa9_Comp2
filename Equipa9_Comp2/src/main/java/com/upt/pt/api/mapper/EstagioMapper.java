package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.EstagioDTO;
import com.upt.pt.api.entity.*;

public final class EstagioMapper {

    private EstagioMapper() {
    }

    public static EstagioDTO toDTO(Estagio entity) {
        if (entity == null) return null;

        EstagioDTO dto = new EstagioDTO();
        dto.setId(entity.getId());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        dto.setEstadoFinal(entity.getEstadoFinal());
        dto.setNotaFinal(entity.getNotaFinal());
        dto.setObservacoes(entity.getObservacoes());

        Estudante est = entity.getEstudante();
        if (est != null) {
            dto.setEstudanteId(est.getId());
            dto.setEstudanteNome(est.getNome());
        }

        OfertaEstagio oferta = entity.getOferta();
        if (oferta != null) {
            dto.setOfertaId(oferta.getId());
            dto.setOfertaTitulo(oferta.getTitulo());
        }

        Curso curso = entity.getCurso();
        if (curso != null) {
            dto.setCursoId(curso.getId());
            dto.setCursoNome(curso.getNome());
        }

        Empresa emp = entity.getEmpresa();
        if (emp != null) {
            dto.setEmpresaId(emp.getId());
            dto.setEmpresaNome(emp.getNome());
        }

        return dto;
    }

    public static Estagio toEntity(EstagioDTO dto) {
        if (dto == null) return null;

        Estagio e = new Estagio();
        e.setDataInicio(dto.getDataInicio());
        e.setDataFim(dto.getDataFim());
        e.setEstadoFinal(dto.getEstadoFinal());
        e.setNotaFinal(dto.getNotaFinal());
        e.setObservacoes(dto.getObservacoes());
        // estudante, oferta, curso, empresa são ligados no service
        return e;
    }
}
