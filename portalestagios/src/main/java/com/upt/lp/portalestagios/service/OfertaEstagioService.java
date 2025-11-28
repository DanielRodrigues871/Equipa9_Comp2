package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import com.upt.lp.portalestagios.repository.OfertaEstagioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfertaEstagioService {

    private final OfertaEstagioRepository repo;

    public OfertaEstagioService(OfertaEstagioRepository repo) {
        this.repo = repo;
    }

    // ----------------------
    // CRUD
    // ----------------------

    public List<OfertaEstagio> listar() {
        return repo.findAll();
    }

    public OfertaEstagio buscar(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));
    }

    public OfertaEstagio criar(OfertaEstagio oferta) {
        return repo.save(oferta);
    }

    public OfertaEstagio atualizar(UUID id, OfertaEstagio dados) {
        OfertaEstagio o = buscar(id);

        o.setTitulo(dados.getTitulo());
        o.setDescricao(dados.getDescricao());
        o.setEmpresa(dados.getEmpresa());
        o.setCoordenadorResponsavel(dados.getCoordenadorResponsavel());
        o.setCurso(dados.getCurso());
        o.setArea(dados.getArea());
        o.setTipo(dados.getTipo());
        o.setNumeroVagas(dados.getNumeroVagas());
        o.setDataInicio(dados.getDataInicio());
        o.setDataFim(dados.getDataFim());
        o.setLocalizacao(dados.getLocalizacao());
        o.setRequisitos(dados.getRequisitos());

        return repo.save(o);
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }

    // ----------------------
    // AÇÕES DE NEGÓCIO
    // ----------------------

    public OfertaEstagio aprovar(UUID id) {
        OfertaEstagio o = buscar(id);
        o.aprovar();
        return repo.save(o);
    }

    public OfertaEstagio rejeitar(UUID id) {
        OfertaEstagio o = buscar(id);
        o.rejeitar();
        return repo.save(o);
    }
}
