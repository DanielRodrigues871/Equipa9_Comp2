package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.enums.StatusCandidatura;
import com.upt.lp.portalestagios.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;

    @Autowired
    public CandidaturaService(CandidaturaRepository candidaturaRepository) {
        this.candidaturaRepository = candidaturaRepository;
    }

    public List<Candidatura> listarTodas() {
        return candidaturaRepository.findAll();
    }

    public Optional<Candidatura> buscarPorId(String id) {
        return candidaturaRepository.findById(id);
    }

    public Candidatura criar(Candidatura candidatura) {
        candidatura.setStatus(StatusCandidatura.SUBMETIDA);
        candidatura.setDataSubmissao(LocalDateTime.now());
        return candidaturaRepository.save(candidatura);
    }

    public Optional<Candidatura> atualizar(String id, Candidatura candidaturaAtualizada) {
        return candidaturaRepository.findById(id).map(candidatura -> {
            candidatura.setCartaMotivacao(candidaturaAtualizada.getCartaMotivacao());
            candidatura.setStatus(candidaturaAtualizada.getStatus());
            candidatura.setDataAnalise(candidaturaAtualizada.getDataAnalise());
            candidatura.setObservacoes(candidaturaAtualizada.getObservacoes());
            candidatura.setCoordenadorResponsavel(candidaturaAtualizada.getCoordenadorResponsavel());
            // Não atualize campos que não fazem sentido alterar diretamente (ex: estudante, oferta)
            return candidaturaRepository.save(candidatura);
        });
    }

    public boolean deletar(String id) {
        return candidaturaRepository.findById(id).map(candidatura -> {
            candidaturaRepository.delete(candidatura);
            return true;
        }).orElse(false);
    }

    public Optional<Candidatura> colocarEmAnalise(String id) {
        return candidaturaRepository.findById(id).map(candidatura -> {
            candidatura.colocarEmAnalise();
            return candidaturaRepository.save(candidatura);
        });
    }

    public Optional<Candidatura> aprovar(String id) {
        return candidaturaRepository.findById(id).map(candidatura -> {
            candidatura.aprovar();
            return candidaturaRepository.save(candidatura);
        });
    }

    public Optional<Candidatura> rejeitar(String id, String observacoes) {
        return candidaturaRepository.findById(id).map(candidatura -> {
            candidatura.rejeitar(observacoes);
            return candidaturaRepository.save(candidatura);
        });
    }
}
