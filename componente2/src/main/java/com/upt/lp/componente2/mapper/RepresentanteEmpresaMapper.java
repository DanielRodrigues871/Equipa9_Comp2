package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.RepresentanteEmpresaDTO;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;

public class RepresentanteEmpresaMapper {
    
    public static RepresentanteEmpresaDTO toDTO(RepresentanteEmpresa representante) {
        if (representante == null) return null;
        
        RepresentanteEmpresaDTO dto = new RepresentanteEmpresaDTO(
            representante.getId(),
            representante.getNome(),
            representante.getEmail(),
            representante.getDataCriacao(),
            representante.getDataAtualizacao(),
            representante.getCargo()
        );
        
        dto.setTelefone(representante.getTelefone());
        
        if (representante.getEmpresa() != null) {
            dto.setEmpresaId(representante.getEmpresa().getId().toString());
            dto.setEmpresaNome(representante.getEmpresa().getNome());
        }
        
        return dto;
    }
    
    public static RepresentanteEmpresa toEntity(RepresentanteEmpresaDTO dto) {
        if (dto == null) return null;
        
        RepresentanteEmpresa representante = new RepresentanteEmpresa();
        representante.setId(dto.getId());
        representante.setNome(dto.getNome());
        representante.setEmail(dto.getEmail());
        representante.setCargo(dto.getCargo());
        representante.setTelefone(dto.getTelefone());
        
        // Empresa será definida no Service
        return representante;
    }
}
