package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.EstagioDTO;
import com.upt.lp.componente2.entity.Estagio;

public class EstagioMapper {
    
    public static EstagioDTO toDTO(Estagio estagio) {
        if (estagio == null) return null;
        
        EstagioDTO dto = new EstagioDTO(
            estagio.getId(),
            estagio.getEstudante() != null ? estagio.getEstudante().getId() : null,
            estagio.getEstudante() != null ? estagio.getEstudante().getNome() : null,
            estagio.getOferta() != null ? estagio.getOferta().getId() : null,
            estagio.getOferta() != null ? estagio.getOferta().getTitulo() : null
        );
        
        if (estagio.getCurso() != null) {
            dto.setCursoId(estagio.getCurso().getId());
            dto.setCursoNome(estagio.getCurso().getNome());
        }
        
        if (estagio.getEmpresa() != null) {
            dto.setEmpresaId(estagio.getEmpresa().getId());
            dto.setEmpresaNome(estagio.getEmpresa().getNome());
        }
        
        dto.setDataInicio(estagio.getDataInicio());
        dto.setDataFim(estagio.getDataFim());
        dto.setEstadoFinal(estagio.getEstadoFinal());
        dto.setNotaFinal(estagio.getNotaFinal());
        dto.setObservacoes(estagio.getObservacoes());
        
        return dto;
    }
    
    public static Estagio toEntity(EstagioDTO dto) {
        if (dto == null) return null;
        
        Estagio estagio = new Estagio();
        estagio.setId(dto.getId());
        // Estudante, Oferta, Curso e Empresa serão definidos no Service
        estagio.setDataInicio(dto.getDataInicio());
        estagio.setDataFim(dto.getDataFim());
        estagio.setEstadoFinal(dto.getEstadoFinal());
        estagio.setNotaFinal(dto.getNotaFinal());
        estagio.setObservacoes(dto.getObservacoes());
        
        return estagio;
    }
}
