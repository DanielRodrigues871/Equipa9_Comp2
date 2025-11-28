package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.repository.CandidaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidaturaService {

    private final CandidaturaRepository repo;

    public CandidaturaService(CandidaturaRepository repo) {
        this.repo = repo;
    }

    // -------------------------------------
    // CRUD
    // -------------------------------------

    public List<Candidatura> listar() {
        return repo.findAll();
    }

    public Candidatura buscar(String id) {
        UUID uuid = UUID.fromString(id);
        return repo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));
    }

    public Candidatura criar(Candidatura candidatura) {
        return repo.save(candidatura);
    }

    public Candidatura atualizar(String id, Candidatura dados) {
        UUID uuid = UUID.fromString(id);
        Candidatura c = repo.findById(uuid).orElseThrow();

        // Campos que realmente existem ― só estes!
        c.setCartaMotivacao(dados.getCartaMotivacao());
        c.setStatus(dados.getStatus());
        c.setEstudante(dados.getEstudante());
        c.setOferta(dados.getOferta());
        c.setCoordenadorResponsavel(dados.getCoordenadorResponsavel());

        // atualizar observações (se existirem)
        if (dados.getObservacoes() != null) {
            c.rejeitar(dados.getObservacoes()); // usa o método próprio
        }

        return repo.save(c);
    }

    public void apagar(String id) {
        UUID uuid = UUID.fromString(id);
        repo.deleteById(uuid);
    }

    // -------------------------------------
    // AÇÕES ESPECÍFICAS
    // -------------------------------------

    public Candidatura colocarEmAnalise(String id) {
        UUID uuid = UUID.fromString(id);
        Candidatura c = repo.findById(uuid).orElseThrow();

        c.colocarEmAnalise();
        return repo.save(c);
    }

    public Candidatura aprovar(String id) {
        UUID uuid = UUID.fromString(id);
        Candidatura c = repo.findById(uuid).orElseThrow();

        c.aprovar();
        return repo.save(c);
    }

    public Candidatura rejeitar(String id, String motivo) {
        UUID uuid = UUID.fromString(id);
        Candidatura c = repo.findById(uuid).orElseThrow();

        c.rejeitar(motivo);
        return repo.save(c);
    }
}
