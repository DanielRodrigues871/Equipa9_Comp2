package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.representante.RepresentanteEmpresaRequestDTO;
import com.upt.lp.portalestagios.dto.representante.RepresentanteEmpresaResponseDTO;
import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;

public class RepresentanteEmpresaMapper {

    public static RepresentanteEmpresa toEntity(RepresentanteEmpresaRequestDTO dto, Empresa empresa) {
        RepresentanteEmpresa r = new RepresentanteEmpresa(
                dto.getNome(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getCargo()
        );
        r.setEmpresa(empresa);
        return r;
    }

    public static RepresentanteEmpresaResponseDTO toDTO(RepresentanteEmpresa r) {
        RepresentanteEmpresaResponseDTO dto = new RepresentanteEmpresaResponseDTO();

        dto.setId(r.getId());
        dto.setNome(r.getNome());
        dto.setEmail(r.getEmail());
        dto.setCargo(r.getCargo());

        if (r.getEmpresa() != null) {
            dto.setEmpresaId(r.getEmpresa().getId());
            dto.setEmpresaNome(r.getEmpresa().getNome());
        }

        return dto;
    }
}

