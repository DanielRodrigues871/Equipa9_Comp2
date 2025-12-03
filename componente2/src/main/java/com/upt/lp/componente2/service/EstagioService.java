package com.upt.lp.componente2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upt.lp.componente2.entity.*;
import com.upt.lp.componente2.repository.*;

@Service
public class EstagioService {

    private final EstagioRepository estagioRepository;
    private final EstudanteRepository estudanteRepository;
    private final OfertaEstagioRepository ofertaRepository;
    private final CursoRepository cursoRepository;
    private final EmpresaRepository empresaRepository;

    public EstagioService(EstagioRepository estagioRepository,
                          EstudanteRepository estudanteRepository,
                          OfertaEstagioRepository ofertaRepository,
                          CursoRepository cursoRepository,
                          EmpresaRepository empresaRepository) {
        this.estagioRepository = estagioRepository;
        this.estudanteRepository = estudanteRepository;
        this.ofertaRepository = ofertaRepository;
        this.cursoRepository = cursoRepository;
        this.empresaRepository = empresaRepository;
    }

    // CREATE
    public Estagio createEstagio(Estagio e,
                                 String estudanteId,
                                 String ofertaId) {

        validarDadosBaseEstagio(e, estudanteId, ofertaId);

        Estudante est = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));

        OfertaEstagio oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada."));

        e.setEstudante(est);
        e.setOferta(oferta);
        e.setCurso(est.getCurso());
        e.setEmpresa(oferta.getEmpresa());
        e.setEstadoFinal("EM_CURSO");

        return estagioRepository.save(e);
    }

    // READ todos
    public List<Estagio> getAllEstagios() {
        return estagioRepository.findAll();
    }

    // READ por id
    public Estagio getEstagioById(String id) {
        return estagioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estágio não encontrado."));
    }

    // READ por estudante
    public List<Estagio> getEstagiosByEstudante(String estudanteId) {
        return estagioRepository.findByEstudanteId(estudanteId);
    }

    // READ por empresa
    public List<Estagio> getEstagiosByEmpresa(String empresaId) {
        return estagioRepository.findByEmpresaId(empresaId);
    }

    // READ por curso
    public List<Estagio> getEstagiosByCurso(String cursoId) {
        return estagioRepository.findByCursoId(cursoId);
    }

    // READ por estado
    public List<Estagio> getEstagiosByEstado(String estadoFinal) {
        return estagioRepository.findByEstadoFinal(estadoFinal);
    }

    // UPDATE (dados base, não workflow)
    public Estagio updateEstagio(String id, Estagio dados) {
        Estagio existente = getEstagioById(id);

        // apenas campos editáveis fora de workflow
        if (dados.getDataInicio() != null) {
            validarDataInicio(dados.getDataInicio());
            existente.setDataInicio(dados.getDataInicio());
        }
        existente.setObservacoes(dados.getObservacoes());

        return estagioRepository.save(existente);
    }

    // WORKFLOW: concluir
    public Estagio concluirEstagio(String id, String notaFinal, LocalDate dataFim) {
        Estagio e = getEstagioById(id);

        if (dataFim == null) {
            throw new IllegalArgumentException("A data de fim é obrigatória para concluir o estágio.");
        }
        if (e.getDataInicio() != null && dataFim.isBefore(e.getDataInicio())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }

        e.setEstadoFinal("CONCLUIDO");
        e.setNotaFinal(notaFinal);
        e.setDataFim(dataFim);

        return estagioRepository.save(e);
    }

    // WORKFLOW: cancelar
    public Estagio cancelarEstagio(String id, String observacoes) {
        Estagio e = getEstagioById(id);

        if (observacoes == null || observacoes.isBlank()) {
            throw new IllegalArgumentException("Observações são obrigatórias para cancelar o estágio.");
        }

        e.setEstadoFinal("CANCELADO");
        e.setObservacoes(observacoes);

        return estagioRepository.save(e);
    }

    // DELETE
    public void deleteEstagio(String id) {
        Estagio e = getEstagioById(id);
        estagioRepository.delete(e);
    }

    // =========================
    //   MÉTODOS DE VALIDAÇÃO
    // =========================
    private void validarDadosBaseEstagio(Estagio e,
                                         String estudanteId,
                                         String ofertaId) {
        if (e == null) {
            throw new IllegalArgumentException("Estágio não pode ser nulo.");
        }

        if (estudanteId == null || estudanteId.isBlank()) {
            throw new IllegalArgumentException("O estudante é obrigatório.");
        }
        if (!estudanteRepository.existsById(estudanteId)) {
            throw new IllegalArgumentException("O estudante indicado não existe.");
        }

        if (ofertaId == null || ofertaId.isBlank()) {
            throw new IllegalArgumentException("A oferta é obrigatória.");
        }
        if (!ofertaRepository.existsById(ofertaId)) {
            throw new IllegalArgumentException("A oferta indicada não existe.");
        }

        validarDataInicio(e.getDataInicio());
    }

    private void validarDataInicio(LocalDate dataInicio) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início é obrigatória.");
        }
    }
}