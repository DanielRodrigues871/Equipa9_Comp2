package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.coordenador.CoordenadorRequestDTO;
import com.upt.lp.portalestagios.dto.coordenador.CoordenadorResponseDTO;
import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.entity.Departamento;

import java.util.UUID;

public class CoordenadorMapper {

    // request -> entity (usado para criar)
    public static Coordenador toEntity(CoordenadorRequestDTO dto, Departamento departamento) {
        Coordenador c = new Coordenador();
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        if (dto.getPassword() != null) c.setPassword(dto.getPassword()); // Utilizador.setPassword trata hashing
        if (departamento != null) c.setDepartamento(departamento);
        return c;
    }

    // entity -> response
    public static CoordenadorResponseDTO toDTO(Coordenador c) {
        UUID deptId = c.getDepartamento() != null ? c.getDepartamento().getId() : null;
        String deptNome = c.getDepartamento() != null ? c.getDepartamento().getNome() : null;

        return new CoordenadorResponseDTO(
                c.getId(),
                c.getNome(),
                c.getEmail(),
                deptId,
                deptNome
        );
    }
}

