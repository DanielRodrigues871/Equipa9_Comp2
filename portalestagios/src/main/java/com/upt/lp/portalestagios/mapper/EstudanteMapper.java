package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.estudante.EstudanteRequestDTO;
import com.upt.lp.portalestagios.dto.estudante.EstudanteResponseDTO;
import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.entity.Estudante;

import java.util.UUID;

public class EstudanteMapper {

    public static Estudante toEntity(EstudanteRequestDTO dto, Curso curso) {
        Estudante e = new Estudante(
                dto.getNome(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getNumeroEstudante(),
                dto.getAnoMatricula()
        );
        e.setCurso(curso);
        return e;
    }

    public static EstudanteResponseDTO toDTO(Estudante e) {
        EstudanteResponseDTO dto = new EstudanteResponseDTO();

        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setEmail(e.getEmail());
        dto.setNumeroEstudante(e.getNumeroEstudante());
        dto.setAnoMatricula(e.getAnoMatricula());

        if (e.getCurso() != null) {
            dto.setCursoId(e.getCurso().getId());
            dto.setCursoNome(e.getCurso().getNome());
        }

        return dto;
    }
}
