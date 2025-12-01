package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.repository.EstudanteRepository;
import com.upt.lp.componente2.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteService {
    
    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;
    
    public EstudanteService(EstudanteRepository estudanteRepository, 
                           CursoRepository cursoRepository) {
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
    }
    
    public List<Estudante> getAllEstudantes() {
        return estudanteRepository.findAll();
    }
    
    public Estudante getEstudanteById(String id) {
        return estudanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + id));
    }
    
    public Estudante getEstudanteByEmail(String email) {
        return estudanteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com email: " + email));
    }
    
    public Estudante getEstudanteByNumeroEstudante(String numeroEstudante) {
        return estudanteRepository.findByNumeroEstudante(numeroEstudante)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com número: " + numeroEstudante));
    }
    
    public Estudante createEstudante(Estudante estudante, String cursoId) {
        // Verificar se email já existe
        if (estudanteRepository.existsByEmail(estudante.getEmail())) {
            throw new RuntimeException("Já existe um estudante com o email: " + estudante.getEmail());
        }
        
        // Verificar se número de estudante já existe
        if (estudanteRepository.existsByNumeroEstudante(estudante.getNumeroEstudante())) {
            throw new RuntimeException("Já existe um estudante com o número: " + estudante.getNumeroEstudante());
        }
        
        // Buscar e validar curso
        if (cursoId != null) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + cursoId));
            estudante.setCurso(curso);
        }
        
        return estudanteRepository.save(estudante);
    }
    
    public Estudante updateEstudante(String id, Estudante estudanteAtualizado) {
        Estudante estudanteExistente = getEstudanteById(id);
        
        // Verificar se o novo email já existe (se foi alterado)
        if (!estudanteExistente.getEmail().equals(estudanteAtualizado.getEmail()) && 
            estudanteRepository.existsByEmail(estudanteAtualizado.getEmail())) {
            throw new RuntimeException("Já existe um estudante com o email: " + estudanteAtualizado.getEmail());
        }
        
        // Verificar se o novo número de estudante já existe (se foi alterado)
        if (!estudanteExistente.getNumeroEstudante().equals(estudanteAtualizado.getNumeroEstudante()) && 
            estudanteRepository.existsByNumeroEstudante(estudanteAtualizado.getNumeroEstudante())) {
            throw new RuntimeException("Já existe um estudante com o número: " + estudanteAtualizado.getNumeroEstudante());
        }
        
        estudanteExistente.setNome(estudanteAtualizado.getNome());
        estudanteExistente.setEmail(estudanteAtualizado.getEmail());
        estudanteExistente.setNumeroEstudante(estudanteAtualizado.getNumeroEstudante());
        estudanteExistente.setAnoMatricula(estudanteAtualizado.getAnoMatricula());
        estudanteExistente.setMedia(estudanteAtualizado.getMedia());
        
        // Atualizar curso se fornecido
        if (estudanteAtualizado.getCurso() != null) {
            estudanteExistente.setCurso(estudanteAtualizado.getCurso());
        }
        
        // Atualizar competências se fornecidas
        if (estudanteAtualizado.getCompetencias() != null) {
            estudanteExistente.setCompetencias(estudanteAtualizado.getCompetencias());
        }
        
        // Só atualiza a password se foi fornecida uma nova
        if (estudanteAtualizado.getPassword() != null && 
            !estudanteAtualizado.getPassword().equals(estudanteExistente.getPassword())) {
            estudanteExistente.setPassword(estudanteAtualizado.getPassword());
        }
        
        return estudanteRepository.save(estudanteExistente);
    }
    
    public void deleteEstudante(String id) {
        if (!estudanteRepository.existsById(id)) {
            throw new RuntimeException("Estudante não encontrado com ID: " + id);
        }
        estudanteRepository.deleteById(id);
    }
    
    public List<Estudante> getEstudantesByCurso(String cursoId) {
        return estudanteRepository.findByCursoId(cursoId);
    }
    
    public List<Estudante> getEstudantesByAnoMatricula(int anoMatricula) {
        return estudanteRepository.findByAnoMatricula(anoMatricula);
    }
}