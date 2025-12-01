package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Departamento;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import com.upt.lp.componente2.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordenadorService {
    
    private final CoordenadorRepository coordenadorRepository;
    private final DepartamentoRepository departamentoRepository;
    
    public CoordenadorService(CoordenadorRepository coordenadorRepository, 
                             DepartamentoRepository departamentoRepository) {
        this.coordenadorRepository = coordenadorRepository;
        this.departamentoRepository = departamentoRepository;
    }
    
    public List<Coordenador> getAllCoordenadores() {
        return coordenadorRepository.findAll();
    }
    
    public Coordenador getCoordenadorById(String id) {
        return coordenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com ID: " + id));
    }
    
    public Coordenador getCoordenadorByEmail(String email) {
        return coordenadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Coordenador não encontrado com email: " + email));
    }
    
    public Coordenador createCoordenador(Coordenador coordenador, String departamentoId) {
        // Verificar se email já existe
        if (coordenadorRepository.existsByEmail(coordenador.getEmail())) {
            throw new RuntimeException("Já existe um coordenador com o email: " + coordenador.getEmail());
        }
        
        // Buscar e validar departamento
        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com ID: " + departamentoId));
        
        coordenador.setDepartamento(departamento);
        return coordenadorRepository.save(coordenador);
    }
    
    public Coordenador updateCoordenador(String id, Coordenador coordenadorAtualizado) {
        Coordenador coordenadorExistente = getCoordenadorById(id);
        
        // Verificar se o novo email já existe (se foi alterado)
        if (!coordenadorExistente.getEmail().equals(coordenadorAtualizado.getEmail()) && 
            coordenadorRepository.existsByEmail(coordenadorAtualizado.getEmail())) {
            throw new RuntimeException("Já existe um coordenador com o email: " + coordenadorAtualizado.getEmail());
        }
        
        coordenadorExistente.setNome(coordenadorAtualizado.getNome());
        coordenadorExistente.setEmail(coordenadorAtualizado.getEmail());
        
        // Atualizar departamento se fornecido
        if (coordenadorAtualizado.getDepartamento() != null) {
            coordenadorExistente.setDepartamento(coordenadorAtualizado.getDepartamento());
        }
        
        // Só atualiza a password se foi fornecida uma nova
        if (coordenadorAtualizado.getPassword() != null && 
            !coordenadorAtualizado.getPassword().equals(coordenadorExistente.getPassword())) {
            coordenadorExistente.setPassword(coordenadorAtualizado.getPassword());
        }
        
        return coordenadorRepository.save(coordenadorExistente);
    }
    
    public void deleteCoordenador(String id) {
        if (!coordenadorRepository.existsById(id)) {
            throw new RuntimeException("Coordenador não encontrado com ID: " + id);
        }
        coordenadorRepository.deleteById(id);
    }
    
    public List<Coordenador> getCoordenadoresByDepartamento(String departamentoId) {
        return coordenadorRepository.findByDepartamentoId(departamentoId);
    }
}
