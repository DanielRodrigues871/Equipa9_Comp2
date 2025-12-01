package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    
    private final EmpresaRepository empresaRepository;
    
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
    
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }
    
    public Empresa getEmpresaById(String id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElseThrow(() -> 
            new RuntimeException("Empresa não encontrada com ID: " + id));
    }
    
    public Empresa getEmpresaByNif(String nif) {
        Optional<Empresa> empresa = empresaRepository.findByNif(nif);
        return empresa.orElseThrow(() -> 
            new RuntimeException("Empresa não encontrada com NIF: " + nif));
    }
    
    public Empresa createEmpresa(Empresa empresa) {
        // Verificar se NIF já existe
        if (empresaRepository.existsByNif(empresa.getNif())) {
            throw new RuntimeException("Já existe uma empresa com o NIF: " + empresa.getNif());
        }
        
        // Verificar se email já existe
        if (empresa.getEmail() != null && empresaRepository.existsByEmail(empresa.getEmail())) {
            throw new RuntimeException("Já existe uma empresa com o email: " + empresa.getEmail());
        }
        
        return empresaRepository.save(empresa);
    }
    
    public Empresa updateEmpresa(String id, Empresa empresaAtualizada) {
        Empresa empresaExistente = getEmpresaById(id);
        
        // Verificar se o novo NIF já existe (se foi alterado)
        if (!empresaExistente.getNif().equals(empresaAtualizada.getNif()) && 
            empresaRepository.existsByNif(empresaAtualizada.getNif())) {
            throw new RuntimeException("Já existe uma empresa com o NIF: " + empresaAtualizada.getNif());
        }
        
        // Verificar se o novo email já existe (se foi alterado)
        if (empresaAtualizada.getEmail() != null && 
            !empresaAtualizada.getEmail().equals(empresaExistente.getEmail()) && 
            empresaRepository.existsByEmail(empresaAtualizada.getEmail())) {
            throw new RuntimeException("Já existe uma empresa com o email: " + empresaAtualizada.getEmail());
        }
        
        empresaExistente.setNome(empresaAtualizada.getNome());
        empresaExistente.setNif(empresaAtualizada.getNif());
        empresaExistente.setEmail(empresaAtualizada.getEmail());
        empresaExistente.setMorada(empresaAtualizada.getMorada());
        empresaExistente.setTelefone(empresaAtualizada.getTelefone());
        empresaExistente.setWebsite(empresaAtualizada.getWebsite());
        empresaExistente.setDescricao(empresaAtualizada.getDescricao());
        empresaExistente.setAtiva(empresaAtualizada.getAtiva());
        
        return empresaRepository.save(empresaExistente);
    }
    
    public void deleteEmpresa(String id) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa não encontrada com ID: " + id);
        }
        empresaRepository.deleteById(id);
    }
    
    public List<Empresa> searchEmpresasByNome(String nome) {
        return empresaRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public List<Empresa> getEmpresasAtivas() {
        return empresaRepository.findByAtiva(true);
    }
    
    public List<Empresa> getEmpresasInativas() {
        return empresaRepository.findByAtiva(false);
    }
    
    public void ativarEmpresa(String id) {
        Empresa empresa = getEmpresaById(id);
        empresa.setAtiva(true);
        empresaRepository.save(empresa);
    }
    
    public void desativarEmpresa(String id) {
        Empresa empresa = getEmpresaById(id);
        empresa.setAtiva(false);
        empresaRepository.save(empresa);
    }
}