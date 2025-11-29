package com.upt.lp.portalestagios.mapper;

import com.upt.lp.portalestagios.dto.candidatura.CandidaturaRequestDTO;
import com.upt.lp.portalestagios.dto.candidatura.CandidaturaResponseDTO;
import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.entity.OfertaEstagio;
import java.util.UUID;

public class CandidaturaMapper {

    public static CandidaturaResponseDTO toResponseDTO(Candidatura c) {
        if (c == null) return null;
        CandidaturaResponseDTO dto = new CandidaturaResponseDTO();
        dto.setId(c.getId());
        if (c.getEstudante() != null) {
        	dto.setEstudanteId(UUID.fromString(c.getEstudante().getId()));

            dto.setEstudanteNome(c.getEstudante().getNome());
        }
        if (c.getOferta() != null) {
            dto.setOfertaId(c.getOferta().getId());
            dto.setOfertaTitulo(c.getOferta().getTitulo());
        }
        dto.setStatus(c.getStatus());
        dto.setCartaMotivacao(c.getCartaMotivacao());
        dto.setObservacoes(c.getObservacoes());
        dto.setDataSubmissao(c.getDataSubmissao());
        dto.setDataAnalise(c.getDataAnalise());
        return dto;
    }

    /**
     * Converte request DTO -> entidade (parcial). NOTA: este método **não** resolve
     * associações (estudante/oferta). O serviço deve buscar as entidades e
     * associá-las antes de persistir.
     */
    public static Candidatura fromRequestDTO(CandidaturaRequestDTO dto) {
        if (dto == null) return null;
        Candidatura c = new Candidatura();
        c.setCartaMotivacao(dto.getCartaMotivacao());
        // observações/data são definidas nas ações (ou pelo serviço)
        return c;
    }
}
