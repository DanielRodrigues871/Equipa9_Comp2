package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import com.upt.lp.portalestagios.repository.AreaEstagioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaEstagioService {

    private final AreaEstagioRepository repository;

    public AreaEstagioService(AreaEstagioRepository repository) {
        this.repository = repository;
    }

    public List<AreaEstagio> listarTodos() {
        return repository.findAll();
    }

    public AreaEstagio buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
    }

    public AreaEstagio criar(AreaEstagio area) {
        return repository.save(area);
    }

    public AreaEstagio atualizar(String id, AreaEstagio dados) {
        AreaEstagio area = buscarPorId(id);
        area.setNome(dados.getNome());
        area.setDescricao(dados.getDescricao());
        return repository.save(area);
    }

    public void apagar(String id) {
        repository.deleteById(id);
    }
}
