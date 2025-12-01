package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.entity.Departamento;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.repository.CursoRepository;
import com.upt.lp.componente2.repository.DepartamentoRepository;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
    
    private final CursoRepository cursoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CoordenadorRepository coordenadorRepository;
    
    public CursoService(CursoRepository cursoRepository,
                       DepartamentoRepository departamentoRepository,
                       CoordenadorRepository coordenadorRepository) {
        this.cursoRepository = cursoRepository;
        this.departamentoRepository = departamentoRepository;
        this.coordenadorRepository = coordenadorRepository;
    }
    
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }
    
    public Curso getCursoById(String id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
    }
    
    public Curso createCurso(Curso curso, String departamentoId, String coordenadorId) {
        // Verificar se código já existe
        if (cursoRepository.existsByCodigo(curso.getCodigo())) {
            throw new RuntimeException("Já existe um curso com o código: " + curso.getCodigo());
        }
        
        // Buscar departamento
        if (departamentoId != null) {
            Departamento departamento = departamentoRepository.findById(departamentoId)
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado com ID: " + departamentoId));
            curso.setDepartamento(departamento);
        }
        
        // Buscar coordenador
        if (coordenadorId != null) {
            Coordenador coordenador = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com ID: " + coordenadorId));
            curso.setCoordenador(coordenador);
        }
        
        return cursoRepository.save(curso);
    }
    
    public Curso updateCurso(String id, Curso cursoAtualizado) {
        Curso cursoExistente = getCursoById(id);
        
        // Verificar se o novo código já existe (se foi alterado)
        if (!cursoExistente.getCodigo().equals(cursoAtualizado.getCodigo()) && 
            cursoRepository.existsByCodigo(cursoAtualizado.getCodigo())) {
            throw new RuntimeException("Já existe um curso com o código: " + cursoAtualizado.getCodigo());
        }
        
        cursoExistente.setNome(cursoAtualizado.getNome());
        cursoExistente.setCodigo(cursoAtualizado.getCodigo());
        cursoExistente.setDuracaoAnos(cursoAtualizado.getDuracaoAnos());
        cursoExistente.setGrau(cursoAtualizado.getGrau());
        
        // Atualizar departamento se fornecido
        if (cursoAtualizado.getDepartamento() != null) {
            cursoExistente.setDepartamento(cursoAtualizado.getDepartamento());
        }
        
        // Atualizar coordenador se fornecido
        if (cursoAtualizado.getCoordenador() != null) {
            cursoExistente.setCoordenador(cursoAtualizado.getCoordenador());
        }
        
        return cursoRepository.save(cursoExistente);
    }
    
    public void deleteCurso(String id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado com ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
    
    public List<Curso> getCursosByDepartamento(String departamentoId) {
        return cursoRepository.findByDepartamentoId(departamentoId);
    }
    
    public List<Curso> getCursosByCoordenador(String coordenadorId) {
        return cursoRepository.findByCoordenadorId(coordenadorId);
    }
    
    public List<Curso> searchCursosByNome(String nome) {
        return cursoRepository.findByNomeContainingIgnoreCase(nome);
    }
}