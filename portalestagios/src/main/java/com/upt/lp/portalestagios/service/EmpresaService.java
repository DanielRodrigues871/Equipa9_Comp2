package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaService {

    private final EmpresaRepository repo;

    public EmpresaService(EmpresaRepository repo) {
        this.repo = repo;
    }

    /** LISTAR TODAS AS EMPRESAS */
    public List<Empresa> listar() {
        return repo.findAll();
    }

    /** BUSCAR EMPRESA POR ID */
    public Empresa buscar(String id) {
        UUID uuid = UUID.fromString(id);
        return repo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    /** CRIAR EMPRESA */
    public Empresa criar(Empresa empresa) {
        return repo.save(empresa);
    }

    /** ATUALIZAR EMPRESA */
    public Empresa atualizar(String id, Empresa dados) {
        UUID uuid = UUID.fromString(id);

        Empresa e = repo.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        e.setNome(dados.getNome());
        e.setNif(dados.getNif());
        e.setEmail(dados.getEmail());
        e.setMorada(dados.getMorada());
        e.setTelefone(dados.getTelefone());
        e.setWebsite(dados.getWebsite());
        e.setAtiva(dados.isAtiva());

        return repo.save(e);
    }

    /** APAGAR EMPRESA */
    public void apagar(String id) {
        UUID uuid = UUID.fromString(id);
        repo.deleteById(uuid);
    }
}
