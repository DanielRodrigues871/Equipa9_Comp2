package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Estagio;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.repository.EstagioRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;
import com.upt.lp.componente2.repository.OfertaEstagioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EstagioService {
    
    private final EstagioRepository estagioRepository;
    private final EstudanteRepository estudanteRepository;
    private final OfertaEstagioRepository ofertaRepository;
    
    public EstagioService(EstagioRepository estagioRepository,
                         EstudanteRepository estudanteRepository,
                         OfertaEstagioRepository ofertaRepository) {
        this.estagioRepository = estagioRepository;
        this.estudanteRepository = estudanteRepository;
        this.ofertaRepository = ofertaRepository;
    }
    
    public List<Estagio> getAllEstagios() {
        return estagioRepository.findAll();
    }
    
    public Estagio getEstagioById(String id) {
        return estagioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estágio não encontrado com ID: " + id));
    }
    
    public Estagio createEstagio(Estagio estagio, String estudanteId, String ofertaId) {
        // Buscar estudante
        Estudante estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + estudanteId));
        
        // Buscar oferta
        OfertaEstagio oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada com ID: " + ofertaId));
        
        estagio.setEstudante(estudante);
        estagio.setOferta(oferta);
        estagio.setCurso(estudante.getCurso());
        estagio.setEmpresa(oferta.getEmpresa());
        
        return estagioRepository.save(estagio);
    }
    
    public Estagio updateEstagio(String id, Estagio estagioAtualizado) {
        Estagio estagioExistente = getEstagioById(id);
        
        estagioExistente.setDataInicio(estagioAtualizado.getDataInicio());
        estagioExistente.setDataFim(estagioAtualizado.getDataFim());
        estagioExistente.setEstadoFinal(estagioAtualizado.getEstadoFinal());
        estagioExistente.setNotaFinal(estagioAtualizado.getNotaFinal());
        estagioExistente.setObservacoes(estagioAtualizado.getObservacoes());
        
        return estagioRepository.save(estagioExistente);
    }
    
    public void concluirEstagio(String id, String notaFinal, LocalDate dataFim) {
        Estagio estagio = getEstagioById(id);
        estagio.concluir(notaFinal, dataFim);
        estagioRepository.save(estagio);
    }
    
    public void cancelarEstagio(String id, String observacoes) {
        Estagio estagio = getEstagioById(id);
        estagio.cancelar(observacoes);
        estagioRepository.save(estagio);
    }
    
    public void deleteEstagio(String id) {
        if (!estagioRepository.existsById(id)) {
            throw new RuntimeException("Estágio não encontrado com ID: " + id);
        }
        estagioRepository.deleteById(id);
    }
    
    public List<Estagio> getEstagiosByEstudante(String estudanteId) {
        return estagioRepository.findByEstudanteId(estudanteId);
    }
    
    public List<Estagio> getEstagiosByEmpresa(String empresaId) {
        return estagioRepository.findByEmpresaId(empresaId);
    }
    
    public List<Estagio> getEstagiosByCurso(String cursoId) {
        return estagioRepository.findByCursoId(cursoId);
    }
    
    public List<Estagio> getEstagiosByEstado(String estado) {
        return estagioRepository.findByEstadoFinal(estado);
    }
    
    public List<Estagio> getEstagiosConcluidos() {
        return estagioRepository.findByEstadoFinal("CONCLUIDO");
    }
    
    public List<Estagio> getEstagiosEmCurso() {
        return estagioRepository.findByEstadoFinal("EM_CURSO");
    }
}