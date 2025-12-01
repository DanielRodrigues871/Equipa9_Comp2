package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.DepartamentoDTO;
import com.upt.lp.componente2.entity.Departamento;

public class DepartamentoMapper {
    
    public static DepartamentoDTO toDTO(Departamento departamento) {
        if (departamento == null) return null;
        
        DepartamentoDTO dto = new DepartamentoDTO(
            departamento.getId(),
            departamento.getNome(),
            departamento.getCodigo()
        );
        
        dto.setDescricao(departamento.getDescricao());
        
        return dto;
    }
    
    public static Departamento toEntity(DepartamentoDTO dto) {
        if (dto == null) return null;
        
        Departamento departamento = new Departamento();
        departamento.setId(dto.getId());
        departamento.setNome(dto.getNome());
        departamento.setCodigo(dto.getCodigo());
        departamento.setDescricao(dto.getDescricao());
        
        return departamento;
    }
}
