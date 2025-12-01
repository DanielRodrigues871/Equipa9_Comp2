package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.EmpresaDTO;
import com.upt.pt.api.entity.Empresa;

public final class EmpresaMapper {

    private EmpresaMapper() {
    }

    public static EmpresaDTO toDTO(Empresa entity) {
        if (entity == null) {
            return null;
        }

        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setNif(entity.getNif());
        dto.setMorada(entity.getMorada());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setWebsite(entity.getWebsite());
        dto.setDescricao(entity.getDescricao());
        dto.setAtiva(entity.isAtiva());
        dto.setDataCriacao(entity.getDataCriacao());

        if (entity.getRepresentantes() != null) {
            dto.setNumeroRepresentantes(entity.getRepresentantes().size());
        }
        if (entity.getOfertasSubmetidas() != null) {
            dto.setNumeroOfertasSubmetidas(entity.getOfertasSubmetidas().size());
        }

        return dto;
    }

    public static Empresa toEntity(EmpresaDTO dto) {
        if (dto == null) {
            return null;
        }

        Empresa e = new Empresa();
        e.setNome(dto.getNome());
        e.setNif(dto.getNif());
        e.setMorada(dto.getMorada());
        e.setTelefone(dto.getTelefone());
        e.setEmail(dto.getEmail());
        e.setWebsite(dto.getWebsite());
        e.setDescricao(dto.getDescricao());
        e.setAtiva(dto.isAtiva());
        return e;
    }
}
