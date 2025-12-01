package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.PropostaEstagioDTO;
import com.upt.lp.componente2.entity.PropostaEstagio;
import java.util.stream.Collectors;

public class PropostaEstagioMapper {
    
    public static PropostaEstagioDTO toDTO(PropostaEstagio proposta) {
        if (proposta == null) return null;
        
        PropostaEstagioDTO dto = new PropostaEstagioDTO(
            proposta.getId() != null ? Long.parseLong(proposta.getId()) : null, // Converter String para Long
            proposta.getTitulo(),
            proposta.getDescricao(),
            proposta.getLocalizacao(),
            proposta.getDuracaoMeses(),
            proposta.getRemunerado(), // CORRIGIDO: usar getRemunerado()
            proposta.getVagasDisponiveis(),
            proposta.getTipo()
        );
        
        dto.setRequisitos(proposta.getRequisitos());
        dto.setBeneficios(proposta.getBeneficios());
        dto.setValorRemuneracao(proposta.getValorRemuneracao());
        dto.setStatus(proposta.getStatus());
        dto.setDataProposta(proposta.getDataProposta());
        
        if (proposta.getEmpresa() != null) {
            dto.setEmpresaId(proposta.getEmpresa().getId());
            dto.setEmpresaNome(proposta.getEmpresa().getNome());
        }
        
        if (proposta.getRepresentante() != null) {
            dto.setRepresentanteId(proposta.getRepresentante().getId());
            dto.setRepresentanteNome(proposta.getRepresentante().getNome());
        }
        
        if (proposta.getAreas() != null) {
            dto.setAreasIds(proposta.getAreas().stream()
                .map(area -> area.getId())
                .collect(Collectors.toList()));
            
            dto.setAreasNomes(proposta.getAreas().stream()
                .map(area -> area.getNome())
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public static PropostaEstagio toEntity(PropostaEstagioDTO dto) {
        if (dto == null) return null;
        
        PropostaEstagio proposta = new PropostaEstagio();
        // CORRIGIDO: Converter Long para String
        proposta.setId(dto.getId() != null ? dto.getId().toString() : null);
        proposta.setTitulo(dto.getTitulo());
        proposta.setDescricao(dto.getDescricao());
        proposta.setRequisitos(dto.getRequisitos());
        proposta.setBeneficios(dto.getBeneficios());
        proposta.setLocalizacao(dto.getLocalizacao());
        proposta.setDuracaoMeses(dto.getDuracaoMeses());
        proposta.setRemunerado(dto.isRemunerado()); // CORRIGIDO: usar isRemunerado() para DTO
        proposta.setValorRemuneracao(dto.getValorRemuneracao());
        proposta.setVagasDisponiveis(dto.getVagasDisponiveis());
        proposta.setTipo(dto.getTipo());
        proposta.setStatus(dto.getStatus());
        proposta.setDataProposta(dto.getDataProposta());
        
        // Empresa, Representante e Areas serão definidos no Service
        return proposta;
    }
}