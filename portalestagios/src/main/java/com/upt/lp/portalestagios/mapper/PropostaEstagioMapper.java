package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.proposta.PropostaEstagioResponseDTO;
import com.upt.lp.portalestagios.entity.PropostaEstagio;

public class PropostaEstagioMapper {

    public static PropostaEstagioResponseDTO toDTO(PropostaEstagio p) {
        PropostaEstagioResponseDTO dto = new PropostaEstagioResponseDTO();

        dto.setId(p.getId());

        if (p.getArea() != null) {
            dto.setAreaId(p.getArea().getId());
            dto.setAreaNome(p.getArea().getNome());
        }

        if (p.getEmpresa() != null) {
            dto.setEmpresaId(p.getEmpresa().getId());
            dto.setEmpresaNome(p.getEmpresa().getNome());
        }

        if (p.getCoordenadorResponsavel() != null) {
            dto.setCoordenadorId(p.getCoordenadorResponsavel().getId());
            dto.setCoordenadorNome(p.getCoordenadorResponsavel().getNome());
        }

        dto.setTitulo(p.getTitulo());
        dto.setDescricao(p.getDescricao());
        dto.setStatus(p.getStatus());
        dto.setDataRegisto(p.getDataRegisto());

        return dto;
    }
}

