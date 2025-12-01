package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.CursoDTO;
import com.upt.lp.componente2.entity.Curso;

public class CursoMapper {
    
    public static CursoDTO toDTO(Curso curso) {
        if (curso == null) return null;
        
        CursoDTO dto = new CursoDTO(
            curso.getId(),
            curso.getNome(),
            curso.getCodigo(),
            curso.getDuracaoAnos(),
            curso.getGrau()
        );
        
        if (curso.getCoordenador() != null) {
            dto.setCoordenadorId(curso.getCoordenador().getId());
            dto.setCoordenadorNome(curso.getCoordenador().getNome());
        }
        
        if (curso.getDepartamento() != null) {
            dto.setDepartamentoId(curso.getDepartamento().getId());
            dto.setDepartamentoNome(curso.getDepartamento().getNome());
        }
        
        return dto;
    }
    
    public static Curso toEntity(CursoDTO dto) {
        if (dto == null) return null;
        
        Curso curso = new Curso();
        curso.setId(dto.getId());
        curso.setNome(dto.getNome());
        curso.setCodigo(dto.getCodigo());
        curso.setDuracaoAnos(dto.getDuracaoAnos());
        curso.setGrau(dto.getGrau());
        
        // Coordenador e Departamento serão definidos no Service
        return curso;
    }
}
