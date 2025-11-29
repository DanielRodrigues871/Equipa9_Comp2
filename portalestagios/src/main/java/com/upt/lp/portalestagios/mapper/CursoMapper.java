package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.curso.CursoRequestDTO;
import com.upt.lp.portalestagios.dto.curso.CursoResponseDTO;
import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Departamento;

public class CursoMapper {

    // DTO → ENTIDADE (usado no create e update)
    public static Curso toEntity(CursoRequestDTO dto, Departamento departamento) {
        Curso c = new Curso();
        c.setNome(dto.getNome());
        c.setCodigo(dto.getCodigo());
        c.setDuracaoAnos(dto.getDuracaoAnos());
        c.setGrau(dto.getGrau());
        c.setDepartamento(departamento);
        return c;
    }

    // ENTIDADE → DTO (para respostas)
    public static CursoResponseDTO toDTO(Curso c) {
        return new CursoResponseDTO(
                c.getId(),
                c.getNome(),
                c.getCodigo(),
                c.getDuracaoAnos(),
                c.getGrau(),
                c.getDepartamento() != null ? c.getDepartamento().getNome() : null
        );
    }
}
