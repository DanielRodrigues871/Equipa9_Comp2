package com.upt.pt.api.mapper;

import com.upt.pt.api.dto.PropostaEstagioDTO;
import com.upt.pt.api.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public final class PropostaEstagioMapper {

    private PropostaEstagioMapper() {
    }

    public static PropostaEstagioDTO toDTO(PropostaEstagio entity) {
        if (entity == null) return null;

        PropostaEstagioDTO dto = new PropostaEstagioDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setRequisitos(entity.getRequisitos());
        dto.setBeneficios(entity.getBeneficios());
        dto.setLocalizacao(entity.getLocalizacao());
        dto.setDuracaoMeses(entity.getDuracaoMeses());
        dto.setRemunerado(entity.isRemunerado());
        dto.setValorRemuneracao(entity.getValorRemuneracao());
        dto.setVagasDisponiveis(entity.getVagasDisponiveis());
        dto.setTipo(entity.getTipo());
        dto.setStatus(entity.getStatus());
        dto.setDataProposta(entity.getDataProposta());

        Empresa emp = entity.getEmpresa();
        if (emp != null) {
            dto.setEmpresaId(null); 
            dto.setEmpresaNome(emp.getNome());
        }

        RepresentanteEmpresa rep = entity.getRepresentante();
        if (rep != null) {
            dto.setRepresentanteId(rep.getId());
            dto.setRepresentanteNome(rep.getNome());
        }

        List<AreaEstagio> areas = entity.getAreas();
        if (areas != null && !areas.isEmpty()) {
            dto.setAreasIds(
                    areas.stream().map(AreaEstagio::getId).collect(Collectors.toList())
            );
            dto.setAreasNomes(
                    areas.stream().map(AreaEstagio::getNome).collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static PropostaEstagio toEntity(PropostaEstagioDTO dto) {
        if (dto == null) return null;

        PropostaEstagio p = new PropostaEstagio();
        p.setTitulo(dto.getTitulo());
        p.setDescricao(dto.getDescricao());
        p.setRequisitos(dto.getRequisitos());
        p.setBeneficios(dto.getBeneficios());
        p.setLocalizacao(dto.getLocalizacao());
        p.setDuracaoMeses(dto.getDuracaoMeses());
        p.setRemunerado(dto.isRemunerado());
        p.setValorRemuneracao(dto.getValorRemuneracao());
        p.setVagasDisponiveis(dto.getVagasDisponiveis());
        p.setTipo(dto.getTipo());
        p.setStatus(dto.getStatus());
        p.setDataProposta(dto.getDataProposta());
        // empresa, representante e áreas são ligados no service
        return p;
    }
}
