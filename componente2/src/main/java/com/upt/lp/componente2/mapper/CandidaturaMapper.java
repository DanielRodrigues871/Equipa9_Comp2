package com.upt.lp.componente2.mapper;

import com.upt.lp.componente2.dto.CandidaturaDTO;
import com.upt.lp.componente2.entity.Candidatura;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.OfertaEstagio;

public final class CandidaturaMapper {

    private CandidaturaMapper() {
    }

    public static CandidaturaDTO toDTO(Candidatura entity) {
        if (entity == null) {
            return null;
        }

        CandidaturaDTO dto = new CandidaturaDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setCartaMotivacao(entity.getCartaMotivacao());
        dto.setDataSubmissao(entity.getDataSubmissao());
        dto.setDataAnalise(entity.getDataAnalise());
        dto.setObservacoes(entity.getObservacoes());

        Estudante est = entity.getEstudante();
        if (est != null) {
            dto.setEstudanteId(est.getId());
            dto.setEstudanteNome(est.getNome());
        }

        OfertaEstagio oferta = entity.getOferta();
        if (oferta != null) {
            dto.setOfertaId(oferta.getId());
            dto.setOfertaTitulo(oferta.getTitulo());
        }

        Coordenador coord = entity.getCoordenadorResponsavel();
        if (coord != null) {
            dto.setCoordenadorResponsavelId(coord.getId());
            dto.setCoordenadorResponsavelNome(coord.getNome());
        }

        return dto;
    }

    public static Candidatura toEntity(CandidaturaDTO dto) {
        if (dto == null) {
            return null;
        }

        Candidatura c = new Candidatura();
        c.setCartaMotivacao(dto.getCartaMotivacao());
        c.setStatus(dto.getStatus() != null ? dto.getStatus() : null);
        c.setObservacoes(dto.getObservacoes());
        // estudante, oferta e coordenador são ligados no service
        return c;
    }
}