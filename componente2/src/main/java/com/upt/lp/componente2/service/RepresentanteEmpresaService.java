package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.repository.RepresentanteEmpresaRepository;
import com.upt.lp.componente2.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentanteEmpresaService {
    
    private final RepresentanteEmpresaRepository representanteRepository;
    private final EmpresaRepository empresaRepository;
    
    public RepresentanteEmpresaService(RepresentanteEmpresaRepository representanteRepository, 
                                      EmpresaRepository empresaRepository) {
        this.representanteRepository = representanteRepository;
        this.empresaRepository = empresaRepository;
    }
    
    public List<RepresentanteEmpresa> getAllRepresentantes() {
        return representanteRepository.findAll();
    }
    
    public RepresentanteEmpresa getRepresentanteById(String id) {
        return representanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado com ID: " + id));
    }
    
    public RepresentanteEmpresa getRepresentanteByEmail(String email) {
        return representanteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado com email: " + email));
    }
    
    public RepresentanteEmpresa createRepresentante(RepresentanteEmpresa representante, String empresaId) {
        // Verificar se email já existe
        if (representanteRepository.existsByEmail(representante.getEmail())) {
            throw new RuntimeException("Já existe um representante com o email: " + representante.getEmail());
        }
        
        // Buscar e validar empresa
        if (empresaId != null) {
            Empresa empresa = empresaRepository.findById(empresaId)
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + empresaId));
            representante.setEmpresa(empresa);
        }
        
        return representanteRepository.save(representante);
    }
    
    public RepresentanteEmpresa updateRepresentante(String id, RepresentanteEmpresa representanteAtualizado) {
        RepresentanteEmpresa representanteExistente = getRepresentanteById(id);
        
        // Verificar se o novo email já existe (se foi alterado)
        if (!representanteExistente.getEmail().equals(representanteAtualizado.getEmail()) && 
            representanteRepository.existsByEmail(representanteAtualizado.getEmail())) {
            throw new RuntimeException("Já existe um representante com o email: " + representanteAtualizado.getEmail());
        }
        
        representanteExistente.setNome(representanteAtualizado.getNome());
        representanteExistente.setEmail(representanteAtualizado.getEmail());
        representanteExistente.setCargo(representanteAtualizado.getCargo());
        representanteExistente.setTelefone(representanteAtualizado.getTelefone());
        
        // Atualizar empresa se fornecida
        if (representanteAtualizado.getEmpresa() != null) {
            representanteExistente.setEmpresa(representanteAtualizado.getEmpresa());
        }
        
        // Só atualiza a password se foi fornecida uma nova
        if (representanteAtualizado.getPassword() != null && 
            !representanteAtualizado.getPassword().equals(representanteExistente.getPassword())) {
            representanteExistente.setPassword(representanteAtualizado.getPassword());
        }
        
        return representanteRepository.save(representanteExistente);
    }
    
    public void deleteRepresentante(String id) {
        if (!representanteRepository.existsById(id)) {
            throw new RuntimeException("Representante não encontrado com ID: " + id);
        }
        representanteRepository.deleteById(id);
    }
    
    public List<RepresentanteEmpresa> getRepresentantesByEmpresa(String empresaId) {
        return representanteRepository.findByEmpresaId(empresaId);
    }
    
    public List<RepresentanteEmpresa> getRepresentantesByCargo(String cargo) {
        return representanteRepository.findByCargo(cargo);
    }
}
