package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.departamento.DepartamentoRequestDTO;
import com.upt.lp.portalestagios.dto.departamento.DepartamentoResponseDTO;
import com.upt.lp.portalestagios.entity.Departamento;

public class DepartamentoMapper {

    // DTO → ENTIDADE
    public static Departamento toEntity(DepartamentoRequestDTO dto) {
        Departamento d = new Departamento();
        d.setNome(dto.getNome());
        d.setCodigo(dto.getCodigo());
        d.setDescricao(dto.getDescricao());
        return d;
    }

    // ENTIDADE → DTO
    public static DepartamentoResponseDTO toDTO(Departamento d) {
        return new DepartamentoResponseDTO(
                d.getId(),
                d.getNome(),
                d.getCodigo(),
                d.getDescricao()
        );
    }
}

