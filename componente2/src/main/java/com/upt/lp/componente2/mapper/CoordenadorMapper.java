package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.CoordenadorDTO;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Departamento;

import java.util.stream.Collectors;

public class CoordenadorMapper {
    
    public static CoordenadorDTO toDTO(Coordenador coordenador) {
        if (coordenador == null) return null;
        
        CoordenadorDTO dto = new CoordenadorDTO(
            coordenador.getId(),
            coordenador.getNome(),
            coordenador.getEmail(),
            coordenador.getDataCriacao(),
            coordenador.getDataAtualizacao(),
            coordenador.getDepartamento() != null ? coordenador.getDepartamento().getId() : null,
            coordenador.getDepartamento() != null ? coordenador.getDepartamento().getNome() : null
        );
        
        // Mapear listas de IDs
        if (coordenador.getCursosGeridos() != null) {
            dto.setCursosGeridosIds(coordenador.getCursosGeridos().stream()
                .map(curso -> curso.getId().toString())
                .collect(Collectors.toList()));
        }
        
        if (coordenador.getOfertasRegistadas() != null) {
            dto.setOfertasRegistadasIds(coordenador.getOfertasRegistadas().stream()
                .map(oferta -> oferta.getId().toString())
                .collect(Collectors.toList()));
        }
        
        if (coordenador.getCandidaturasGeridas() != null) {
            dto.setCandidaturasGeridasIds(coordenador.getCandidaturasGeridas().stream()
                .map(candidatura -> candidatura.getId().toString())
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public static Coordenador toEntity(CoordenadorDTO dto) {
        if (dto == null) return null;
        
        Coordenador coordenador = new Coordenador();
        coordenador.setId(dto.getId());
        coordenador.setNome(dto.getNome());
        coordenador.setEmail(dto.getEmail());
        
        // Departamento será definido no Service
        return coordenador;
    }
}