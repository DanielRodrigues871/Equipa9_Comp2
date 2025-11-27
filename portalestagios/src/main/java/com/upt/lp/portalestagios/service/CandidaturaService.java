package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.enums.StatusCandidatura;
import com.upt.lp.portalestagios.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public List<Candidatura> findAll() {
        return candidaturaRepository.findAll();
    }

    public Optional<Candidatura> findById(String id) {
        return candidaturaRepository.findById(id);
    }

    public Candidatura save(Candidatura candidatura) {
        return candidaturaRepository.save(candidatura);
    }

    public void delete(String id) {
        candidaturaRepository.deleteById(id);
    }

    /** Ações específicas */
    public Candidatura colocarEmAnalise(String id) {
        Candidatura c = candidaturaRepository.findById(id).orElseThrow();
        c.colocarEmAnalise();
        return candidaturaRepository.save(c);
    }

    public Candidatura aprovar(String id) {
        Candidatura c = candidaturaRepository.findById(id).orElseThrow();
        c.aprovar();
        return candidaturaRepository.save(c);
    }

    public Candidatura rejeitar(String id, String motivo) {
        Candidatura c = candidaturaRepository.findById(id).orElseThrow();
        c.rejeitar(motivo);
        return candidaturaRepository.save(c);
    }
}
