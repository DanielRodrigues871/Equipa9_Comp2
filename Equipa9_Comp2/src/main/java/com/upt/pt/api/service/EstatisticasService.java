package com.upt.pt.api.service;

import com.upt.pt.api.dto.EstatisticasDTO;
import com.upt.pt.api.enums.StatusCandidatura;
import com.upt.pt.api.repository.*;
import org.springframework.stereotype.Service;

@Service
public class EstatisticasService {

    private final OfertaEstagioRepository ofertaRepo;
    private final CandidaturaRepository candidaturaRepo;
    private final CursoRepository cursoRepo;
    private final EmpresaRepository empresaRepo;

    public EstatisticasService(OfertaEstagioRepository ofertaRepo,
                               CandidaturaRepository candidaturaRepo,
                               CursoRepository cursoRepo,
                               EmpresaRepository empresaRepo) {
        this.ofertaRepo = ofertaRepo;
        this.candidaturaRepo = candidaturaRepo;
        this.cursoRepo = cursoRepo;
        this.empresaRepo = empresaRepo;
    }

    public EstatisticasDTO obterEstatisticas() {

        EstatisticasDTO dto = new EstatisticasDTO();

        // =========================
        // CANDIDATURAS (percentagens)
        // =========================
        long total = candidaturaRepo.totalCandidaturas();

        long aprovadas = candidaturaRepo.countByStatus(StatusCandidatura.APROVADA);
        long pendentes = candidaturaRepo.countByStatus(StatusCandidatura.SUBMETIDA);
        long rejeitadas = candidaturaRepo.countByStatus(StatusCandidatura.REJEITADA);

        if (total > 0) {
            dto.setPercentAprovadas((aprovadas * 100.0) / total);
            dto.setPercentPendentes((pendentes * 100.0) / total);
            dto.setPercentRejeitadas((rejeitadas * 100.0) / total);
        }

        // =========================
        // OFERTAS
        // =========================
        dto.setOfertasSemCandidaturas(
                ofertaRepo.findOfertasSemCandidaturas().size()
        );

        // =========================
        // RANKINGS
        // =========================
        dto.setCursosMaisProcurados(
                cursoRepo.rankingCursosMaisProcurados()
        );

        dto.setEmpresasMaisProcuradas(
                empresaRepo.rankingEmpresasMaisProcuradas()
        );

        dto.setEmpresasMenosEscolhidas(
                empresaRepo.rankingEmpresasMenosEscolhidas()
        );

        return dto;
    }
}