package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.EstudanteDTO;
import com.upt.lp.componente2.entity.Estudante;

import java.util.stream.Collectors;

public class EstudanteMapper {
    
    public static EstudanteDTO toDTO(Estudante estudante) {
        if (estudante == null) return null;
        
        EstudanteDTO dto = new EstudanteDTO(
            estudante.getId(),
            estudante.getNome(),
            estudante.getEmail(),
            estudante.getDataCriacao(),
            estudante.getDataAtualizacao(),
            estudante.getNumeroEstudante(),
            estudante.getAnoMatricula()
        );
        
        dto.setMedia(estudante.getMedia());
        dto.setCompetencias(estudante.getCompetencias());
        
        // Mapear relacionamentos
        if (estudante.getCurso() != null) {
            dto.setCursoId(estudante.getCurso().getId().toString());
            dto.setCursoNome(estudante.getCurso().getNome());
        }
        
        if (estudante.getCandidaturas() != null) {
            dto.setCandidaturasIds(estudante.getCandidaturas().stream()
                .map(candidatura -> candidatura.getId().toString())
                .collect(Collectors.toList()));
        }
        
        if (estudante.getDocumentos() != null) {
            dto.setDocumentosIds(estudante.getDocumentos().stream()
                .map(documento -> documento.getId().toString())
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public static Estudante toEntity(EstudanteDTO dto) {
        if (dto == null) return null;
        
        Estudante estudante = new Estudante();
        estudante.setId(dto.getId());
        estudante.setNome(dto.getNome());
        estudante.setEmail(dto.getEmail());
        estudante.setNumeroEstudante(dto.getNumeroEstudante());
        estudante.setAnoMatricula(dto.getAnoMatricula());
        estudante.setMedia(dto.getMedia());
        
        if (dto.getCompetencias() != null) {
            estudante.setCompetencias(dto.getCompetencias());
        }
        
        // Curso será definido no Service
        return estudante;
    }
}
