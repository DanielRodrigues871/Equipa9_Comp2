package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.EstudanteDTO;
import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.entity.Estudante;

public final class EstudanteMapper {

    private EstudanteMapper() {
    }

    public static EstudanteDTO toDTO(Estudante entity) {
        if (entity == null) {
            return null;
        }

        EstudanteDTO dto = new EstudanteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setNumeroEstudante(entity.getNumeroEstudante());
        dto.setAnoMatricula(entity.getAnoMatricula());
        dto.setMedia(entity.getMedia());
        dto.setCompetencias(entity.getCompetencias());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataAtualizacao(entity.getDataAtualizacao());

        Curso curso = entity.getCurso();
        if (curso != null) {
            dto.setCursoId(curso.getId());
            dto.setCursoNome(curso.getNome());
        }

        return dto;
    }

    // Para criação/actualização a partir do DTO
    // O Curso em si é carregado no service com cursoRepository.findById(dto.getCursoId())
    public static Estudante toEntity(EstudanteDTO dto) {
        if (dto == null) {
            return null;
        }

        Estudante e = new Estudante();
        e.setNome(dto.getNome());
        e.setEmail(dto.getEmail());
        e.setPassword(null); // password vem num DTO específico ou no body separado
        e.setNumeroEstudante(dto.getNumeroEstudante());
        e.setAnoMatricula(dto.getAnoMatricula());
        e.setMedia(dto.getMedia());
        e.setCompetencias(dto.getCompetencias());

        return e;
    }
}