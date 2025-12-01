package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Departamento;
import com.upt.lp.componente2.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartamentoService {
    
    private final DepartamentoRepository departamentoRepository;
    
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }
    
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }
    
    public Departamento getDepartamentoById(String id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com ID: " + id));
    }
    
    public Departamento createDepartamento(Departamento departamento) {
        // Verificar se código já existe
        if (departamentoRepository.existsByCodigo(departamento.getCodigo())) {
            throw new RuntimeException("Já existe um departamento com o código: " + departamento.getCodigo());
        }
        
        return departamentoRepository.save(departamento);
    }
    
    public Departamento updateDepartamento(String id, Departamento departamentoAtualizado) {
        Departamento departamentoExistente = getDepartamentoById(id);
        
        // Verificar se o novo código já existe (se foi alterado)
        if (!departamentoExistente.getCodigo().equals(departamentoAtualizado.getCodigo()) && 
            departamentoRepository.existsByCodigo(departamentoAtualizado.getCodigo())) {
            throw new RuntimeException("Já existe um departamento com o código: " + departamentoAtualizado.getCodigo());
        }
        
        departamentoExistente.setNome(departamentoAtualizado.getNome());
        departamentoExistente.setCodigo(departamentoAtualizado.getCodigo());
        departamentoExistente.setDescricao(departamentoAtualizado.getDescricao());
        
        return departamentoRepository.save(departamentoExistente);
    }
    
    public void deleteDepartamento(String id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RuntimeException("Departamento não encontrado com ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }
    
    public List<Departamento> searchDepartamentosByNome(String nome) {
        return departamentoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
