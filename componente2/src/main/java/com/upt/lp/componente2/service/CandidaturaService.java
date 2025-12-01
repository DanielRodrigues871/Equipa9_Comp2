package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Candidatura;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.enums.StatusCandidatura;
import com.upt.lp.componente2.repository.CandidaturaRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;
import com.upt.lp.componente2.repository.OfertaEstagioRepository;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidaturaService {
    
    private final CandidaturaRepository candidaturaRepository;
    private final EstudanteRepository estudanteRepository;
    private final OfertaEstagioRepository ofertaRepository;
    private final CoordenadorRepository coordenadorRepository;
    
    public CandidaturaService(CandidaturaRepository candidaturaRepository,
                             EstudanteRepository estudanteRepository,
                             OfertaEstagioRepository ofertaRepository,
                             CoordenadorRepository coordenadorRepository) {
        this.candidaturaRepository = candidaturaRepository;
        this.estudanteRepository = estudanteRepository;
        this.ofertaRepository = ofertaRepository;
        this.coordenadorRepository = coordenadorRepository;
    }
    
    public List<Candidatura> getAllCandidaturas() {
        return candidaturaRepository.findAll();
    }
    
    public Candidatura getCandidaturaById(String id) {
        return candidaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada com ID: " + id));
    }
    
    public Candidatura createCandidatura(String estudanteId, String ofertaId, String cartaMotivacao) {
        // Buscar estudante
        Estudante estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + estudanteId));
        
        // Buscar oferta
        OfertaEstagio oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada com ID: " + ofertaId));
        
        // Verificar se estudante já se candidatou a esta oferta
        if (!candidaturaRepository.findByEstudanteIdAndOfertaId(estudanteId, ofertaId).isEmpty()) {
            throw new RuntimeException("O estudante já se candidatou a esta oferta");
        }
        
        // Criar candidatura
        Candidatura candidatura = new Candidatura(estudante, oferta, cartaMotivacao);
        
        return candidaturaRepository.save(candidatura);
    }
    
    public void colocarEmAnalise(String id, String coordenadorId) {
        Candidatura candidatura = getCandidaturaById(id);
        
        Coordenador coordenador = coordenadorRepository.findById(coordenadorId)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com ID: " + coordenadorId));
        
        candidatura.colocarEmAnalise();
        candidatura.setCoordenadorResponsavel(coordenador);
        
        candidaturaRepository.save(candidatura);
    }
    
    public void aprovarCandidatura(String id) {
        Candidatura candidatura = getCandidaturaById(id);
        candidatura.aprovar();
        candidaturaRepository.save(candidatura);
    }
    
    public void rejeitarCandidatura(String id, String observacoes) {
        Candidatura candidatura = getCandidaturaById(id);
        candidatura.rejeitar(observacoes);
        candidaturaRepository.save(candidatura);
    }
    
    public void deleteCandidatura(String id) {
        if (!candidaturaRepository.existsById(id)) {
            throw new RuntimeException("Candidatura não encontrada com ID: " + id);
        }
        candidaturaRepository.deleteById(id);
    }
    
    public List<Candidatura> getCandidaturasByEstudante(String estudanteId) {
        return candidaturaRepository.findByEstudanteId(estudanteId);
    }
    
    public List<Candidatura> getCandidaturasByOferta(String ofertaId) {
        return candidaturaRepository.findByOfertaId(ofertaId);
    }
    
    public List<Candidatura> getCandidaturasByCoordenador(String coordenadorId) {
        return candidaturaRepository.findByCoordenadorResponsavelId(coordenadorId);
    }
    
    public List<Candidatura> getCandidaturasByStatus(StatusCandidatura status) {
        return candidaturaRepository.findByStatus(status);
    }
    
    public long countCandidaturasByOferta(String ofertaId) {
        return candidaturaRepository.countByOfertaId(ofertaId);
    }
    
    public long countCandidaturasByEstudante(String estudanteId) {
        return candidaturaRepository.countByEstudanteId(estudanteId);
    }
}
