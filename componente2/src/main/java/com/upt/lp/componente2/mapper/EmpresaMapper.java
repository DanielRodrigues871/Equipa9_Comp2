package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.EmpresaDTO;
import com.upt.lp.componente2.entity.Empresa;

public class EmpresaMapper {
    
    public static EmpresaDTO toDTO(Empresa empresa) {
        if (empresa == null) return null;
        
        EmpresaDTO dto = new EmpresaDTO(
            empresa.getId(),
            empresa.getNome(),
            empresa.getNif(),
            empresa.getEmail()
        );
        
        dto.setMorada(empresa.getMorada());
        dto.setTelefone(empresa.getTelefone());
        dto.setWebsite(empresa.getWebsite());
        dto.setDescricao(empresa.getDescricao());
        dto.setAtiva(empresa.getAtiva());
        dto.setDataCriacao(empresa.getDataCriacao());
        
        return dto;
    }
    
    public static Empresa toEntity(EmpresaDTO dto) {
        if (dto == null) return null;
        
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setNome(dto.getNome());
        empresa.setNif(dto.getNif());
        empresa.setEmail(dto.getEmail());
        empresa.setMorada(dto.getMorada());
        empresa.setTelefone(dto.getTelefone());
        empresa.setWebsite(dto.getWebsite());
        empresa.setDescricao(dto.getDescricao());
        empresa.setAtiva(dto.isAtiva());
        empresa.setDataCriacao(dto.getDataCriacao());
        
        return empresa;
    }
}
