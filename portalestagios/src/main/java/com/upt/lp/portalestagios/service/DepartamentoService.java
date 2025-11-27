package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public DepartamentoService(DepartamentoRepository repo) {
        this.repo = repo;
    }

    public List<Departamento> listar() {
        return repo.findAll();
    }

    public Departamento buscar(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
    }

    public Departamento criar(Departamento d) {
        return repo.save(d);
    }

    public Departamento atualizar(String id, Departamento dados) {
        Departamento d = buscar(id);
        d.setNome(dados.getNome());
        d.setCodigo(dados.getCodigo());
        d.setDescricao(dados.getDescricao());
        return repo.save(d);
    }

    public void apagar(String id) {
        repo.deleteById(id);
    }
}
