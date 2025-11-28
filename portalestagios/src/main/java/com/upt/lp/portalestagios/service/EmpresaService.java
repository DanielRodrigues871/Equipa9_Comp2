package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository repo;

    public EmpresaService(EmpresaRepository repo) {
        this.repo = repo;
    }

    public List<Empresa> listar() {
        return repo.findAll();
    }

    public Empresa buscar(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    public Empresa criar(Empresa empresa) {
        return repo.save(empresa);
    }

    public Empresa atualizar(String id, Empresa dados) {
        Empresa e = empresaRepository.findById(id).orElseThrow();

        e.setNome(dados.getNome());
        e.setNif(dados.getNif());
        e.setEmail(dados.getEmail());
        e.setMorada(dados.getMorada());   // <---- CORRETO
        e.setTelefone(dados.getTelefone());
        e.setWebsite(dados.getWebsite());
        e.setAtiva(dados.isAtiva());

        return empresaRepository.save(e);
    }


    public void apagar(String id) {
        repo.deleteById(id);
    }
}
