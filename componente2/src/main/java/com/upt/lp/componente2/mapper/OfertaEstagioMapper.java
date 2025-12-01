package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.OfertaEstagioDTO;
import com.upt.lp.componente2.entity.OfertaEstagio;
import java.util.stream.Collectors;

public class OfertaEstagioMapper {
    
    public static OfertaEstagioDTO toDTO(OfertaEstagio oferta) {
        if (oferta == null) return null;
        
        OfertaEstagioDTO dto = new OfertaEstagioDTO(
            oferta.getId(),
            oferta.getTitulo(),
            oferta.getDescricao(),
            oferta.getEmpresa() != null ? oferta.getEmpresa().getId() : null,
            oferta.getEmpresa() != null ? oferta.getEmpresa().getNome() : null,
            oferta.getTipo()
        );
        
        if (oferta.getArea() != null) {
            dto.setAreaId(oferta.getArea().getId());
            dto.setAreaNome(oferta.getArea().getNome());
        }
        
        if (oferta.getCoordenadorResponsavel() != null) {
            dto.setCoordenadorResponsavelId(oferta.getCoordenadorResponsavel().getId());
            dto.setCoordenadorResponsavelNome(oferta.getCoordenadorResponsavel().getNome());
        }
        
        if (oferta.getCurso() != null) {
            dto.setCursoId(oferta.getCurso().getId());
            dto.setCursoNome(oferta.getCurso().getNome());
        }
        
        dto.setLocalizacao(oferta.getLocalizacao());
        dto.setDuracaoMeses(oferta.getDuracaoMeses());
        dto.setRequisitos(oferta.getRequisitos());
        dto.setDataInicio(oferta.getDataInicio());
        dto.setDataFim(oferta.getDataFim());
        dto.setDataLimiteInscricao(oferta.getDataLimiteInscricao());
        dto.setStatus(oferta.getStatus());
        dto.setNumeroVagas(oferta.getNumeroVagas());
        dto.setDataPublicacao(oferta.getDataPublicacao());
        dto.setDataAprovacao(oferta.getDataAprovacao());
        
        if (oferta.getCandidaturas() != null) {
            dto.setNumeroCandidaturas(oferta.getCandidaturas().size());
        }
        
        return dto;
    }
    
    public static OfertaEstagio toEntity(OfertaEstagioDTO dto) {
        if (dto == null) return null;
        
        OfertaEstagio oferta = new OfertaEstagio();
        oferta.setId(dto.getId());
        oferta.setTitulo(dto.getTitulo());
        oferta.setDescricao(dto.getDescricao());
        oferta.setTipo(dto.getTipo());
        oferta.setLocalizacao(dto.getLocalizacao());
        oferta.setDuracaoMeses(dto.getDuracaoMeses());
        oferta.setRequisitos(dto.getRequisitos());
        oferta.setDataInicio(dto.getDataInicio());
        oferta.setDataFim(dto.getDataFim());
        oferta.setDataLimiteInscricao(dto.getDataLimiteInscricao());
        oferta.setStatus(dto.getStatus());
        oferta.setNumeroVagas(dto.getNumeroVagas());
        oferta.setDataPublicacao(dto.getDataPublicacao());
        oferta.setDataAprovacao(dto.getDataAprovacao());
        
        // Empresa, Area, Coordenador e Curso serão definidos no Service
        return oferta;
    }
}
