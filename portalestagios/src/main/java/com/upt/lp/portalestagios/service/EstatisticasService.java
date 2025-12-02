package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.enums.StatusOferta;
import com.upt.lp.portalestagios.repository.*;
import org.springframework.stereotype.Service;
import com.upt.lp.portalestagios.enums.StatusCandidatura;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstatisticasService {

    private final EstudanteRepository estudanteRepo;
    private final OfertaEstagioRepository ofertaRepo;
    private final CandidaturaRepository candidaturaRepo;
    private final EmpresaRepository empresaRepo;
    private final EstagioRepository estagioRepo;

    public EstatisticasService(EstudanteRepository estudanteRepo,
                               OfertaEstagioRepository ofertaRepo,
                               CandidaturaRepository candidaturaRepo,
                               EmpresaRepository empresaRepo,
                               EstagioRepository estagioRepo) {

        this.estudanteRepo = estudanteRepo;
        this.ofertaRepo = ofertaRepo;
        this.candidaturaRepo = candidaturaRepo;
        this.empresaRepo = empresaRepo;
        this.estagioRepo = estagioRepo;
    }

    // ===============================
    // ESTATÍSTICAS GERAIS
    // ===============================

    public long totalEstudantes() {
        return estudanteRepo.count();
    }

    public long totalEmpresas() {
        return empresaRepo.count();
    }

    public long totalOfertas() {
        return ofertaRepo.count();
    }

    public long totalCandidaturas() {
        return candidaturaRepo.count();
    }

    public long totalEstagios() {
        return estagioRepo.count();
    }

    // ===============================
    // ESTATÍSTICAS DETALHADAS
    // ===============================

    public Map<String, Long> ofertasPorStatus() {
        Map<String, Long> mapa = new HashMap<>();

        mapa.put("Aprovadas",
                ofertaRepo.countByStatus(StatusOferta.APROVADO));

        mapa.put("Rejeitadas",
                ofertaRepo.countByStatus(StatusOferta.REJEITADO));

        mapa.put("Pendentes",
                ofertaRepo.countByStatus(StatusOferta.PENDENTE));

        return mapa;
    }

    public Map<String, Long> candidaturasPorEstado() {
        Map<String, Long> mapa = new HashMap<>();

        mapa.put("Submetidas",
                candidaturaRepo.countByStatus(StatusCandidatura.SUBMETIDA));

        mapa.put("Em Análise",
                candidaturaRepo.countByStatus(StatusCandidatura.EM_ANALISE));

        mapa.put("Aprovadas",
                candidaturaRepo.countByStatus(StatusCandidatura.APROVADA));

        mapa.put("Rejeitadas",
                candidaturaRepo.countByStatus(StatusCandidatura.REJEITADA));

        return mapa;
    }
    public Map<String, Long> ofertasPorEmpresa() {
        Map<String, Long> mapa = new HashMap<>();

        List<Empresa> empresas = empresaRepo.findAll();

        for (Empresa e : empresas) {
            long total = ofertaRepo.findByEmpresaId(e.getId()).size();
            mapa.put(e.getNome(), total);
        }

        return mapa;
    }

    public Map<String, Long> candidaturasPorCurso() {
        Map<String, Long> mapa = new HashMap<>();

        List<Candidatura> lista = candidaturaRepo.findAll();

        for (Candidatura c : lista) {
            String nomeCurso = c.getEstudante().getCurso().getNome();

            mapa.put(nomeCurso, mapa.getOrDefault(nomeCurso, 0L) + 1);
        }

        return mapa;
    }
}
