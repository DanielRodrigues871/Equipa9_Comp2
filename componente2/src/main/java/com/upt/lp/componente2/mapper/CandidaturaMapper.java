package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.CandidaturaDTO;
import com.upt.lp.componente2.entity.Candidatura;
import java.util.stream.Collectors;

public class CandidaturaMapper {
    
    public static CandidaturaDTO toDTO(Candidatura candidatura) {
        if (candidatura == null) return null;
        
        CandidaturaDTO dto = new CandidaturaDTO(
            candidatura.getId(),
            candidatura.getEstudante() != null ? candidatura.getEstudante().getId() : null,
            candidatura.getEstudante() != null ? candidatura.getEstudante().getNome() : null,
            candidatura.getOferta() != null ? candidatura.getOferta().getId() : null,
            candidatura.getOferta() != null ? candidatura.getOferta().getTitulo() : null,
            candidatura.getStatus()
        );
        
        dto.setCartaMotivacao(candidatura.getCartaMotivacao());
        dto.setDataSubmissao(candidatura.getDataSubmissao());
        dto.setDataAnalise(candidatura.getDataAnalise());
        dto.setObservacoes(candidatura.getObservacoes());
        
        if (candidatura.getCoordenadorResponsavel() != null) {
            dto.setCoordenadorResponsavelId(candidatura.getCoordenadorResponsavel().getId());
            dto.setCoordenadorResponsavelNome(candidatura.getCoordenadorResponsavel().getNome());
        }
        
        return dto;
    }
    
    public static Candidatura toEntity(CandidaturaDTO dto) {
        if (dto == null) return null;
        
        Candidatura candidatura = new Candidatura();
        candidatura.setId(dto.getId());
        // Estudante e Oferta serão definidos no Service
        candidatura.setStatus(dto.getStatus());
        candidatura.setCartaMotivacao(dto.getCartaMotivacao());
        // Datas serão geradas automaticamente
        candidatura.setObservacoes(dto.getObservacoes());
        
        return candidatura;
    }
}
