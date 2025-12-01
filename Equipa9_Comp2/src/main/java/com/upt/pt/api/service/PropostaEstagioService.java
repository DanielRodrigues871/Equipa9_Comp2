package com.upt.pt.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.*;
import com.upt.pt.api.repository.*;

@Service
public class PropostaEstagioService {

    private final PropostaEstagioRepository propostaRepository;
    private final EmpresaRepository empresaRepository;
    private final RepresentanteEmpresaRepository representanteRepository;
    private final AreaEstagioRepository areaRepository;

    public PropostaEstagioService(PropostaEstagioRepository propostaRepository,
                                  EmpresaRepository empresaRepository,
                                  RepresentanteEmpresaRepository representanteRepository,
                                  AreaEstagioRepository areaRepository) {
        this.propostaRepository = propostaRepository;
        this.empresaRepository = empresaRepository;
        this.representanteRepository = representanteRepository;
        this.areaRepository = areaRepository;
    }

    // CREATE
    public PropostaEstagio createProposta(PropostaEstagio p,
                                          String empresaId,
                                          String representanteId,
                                          List<String> areasIds) {

        validarDadosProposta(p, empresaId, representanteId);

        Empresa emp = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));
        p.setEmpresa(emp);

        RepresentanteEmpresa rep = representanteRepository.findById(representanteId)
                .orElseThrow(() -> new IllegalArgumentException("Representante não encontrado."));
        p.setRepresentante(rep);

        if (areasIds != null && !areasIds.isEmpty()) {
            List<AreaEstagio> areas = new ArrayList<>();
            for (String areaId : areasIds) {
                AreaEstagio area = areaRepository.findById(areaId)
                        .orElseThrow(() -> new IllegalArgumentException("Área não encontrada: " + areaId));
                areas.add(area);
            }
            p.setAreas(areas);
        }

        p.setStatus("PENDENTE");
        p.setDataProposta(LocalDate.now());

        return propostaRepository.save(p);
    }

    // READ todos
    public List<PropostaEstagio> getAllPropostas() {
        return propostaRepository.findAll();
    }

    // READ por id
    public PropostaEstagio getPropostaById(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));
    }

    // READ por empresa
    public List<PropostaEstagio> getPropostasByEmpresa(String empresaId) {
        return propostaRepository.findByEmpresaId(empresaId);
    }

    // READ por representante
    public List<PropostaEstagio> getPropostasByRepresentante(String representanteId) {
        return propostaRepository.findByRepresentanteId(representanteId);
    }

    // READ por status
    public List<PropostaEstagio> getPropostasByStatus(String status) {
        return propostaRepository.findByStatus(status);
    }

    // UPDATE
    public PropostaEstagio updateProposta(Long id,
                                          PropostaEstagio dados,
                                          String empresaId,
                                          String representanteId,
                                          List<String> areasIds) {

        PropostaEstagio existente = getPropostaById(id);

        validarDadosProposta(dados, empresaId, representanteId);

        Empresa emp = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));
        existente.setEmpresa(emp);

        RepresentanteEmpresa rep = representanteRepository.findById(representanteId)
                .orElseThrow(() -> new IllegalArgumentException("Representante não encontrado."));
        existente.setRepresentante(rep);

        if (areasIds != null) {
            List<AreaEstagio> areas = new ArrayList<>();
            for (String areaId : areasIds) {
                AreaEstagio area = areaRepository.findById(areaId)
                        .orElseThrow(() -> new IllegalArgumentException("Área não encontrada: " + areaId));
                areas.add(area);
            }
            existente.setAreas(areas);
        }

        existente.setTitulo(dados.getTitulo());
        existente.setDescricao(dados.getDescricao());
        existente.setRequisitos(dados.getRequisitos());
        existente.setBeneficios(dados.getBeneficios());
        existente.setLocalizacao(dados.getLocalizacao());
        existente.setDuracaoMeses(dados.getDuracaoMeses());
        existente.setRemunerado(dados.isRemunerado());
        existente.setValorRemuneracao(dados.getValorRemuneracao());
        existente.setVagasDisponiveis(dados.getVagasDisponiveis());
        existente.setTipo(dados.getTipo());

        return propostaRepository.save(existente);
    }

    // WORKFLOW
    public PropostaEstagio aprovarProposta(Long id) {
        PropostaEstagio p = getPropostaById(id);
        p.setStatus("APROVADA");
        return propostaRepository.save(p);
    }

    public PropostaEstagio rejeitarProposta(Long id) {
        PropostaEstagio p = getPropostaById(id);
        p.setStatus("REJEITADA");
        return propostaRepository.save(p);
    }

    // DELETE
    public void deleteProposta(Long id) {
        PropostaEstagio p = getPropostaById(id);
        propostaRepository.delete(p);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosProposta(PropostaEstagio p,
                                      String empresaId,
                                      String representanteId) {
        if (p == null) {
            throw new IllegalArgumentException("Proposta não pode ser nula.");
        }

        if (p.getTitulo() == null || p.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }

        if (p.getDescricao() == null || p.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }

        if (p.getDuracaoMeses() <= 0) {
            throw new IllegalArgumentException("A duração tem de ser maior que zero.");
        }

        if (p.getVagasDisponiveis() <= 0) {
            throw new IllegalArgumentException("O número de vagas tem de ser maior que zero.");
        }

        String tipo = p.getTipo();
        if (tipo == null || (!tipo.equals("CURRICULAR") && !tipo.equals("EXTRA_CURRICULAR"))) {
            throw new IllegalArgumentException("O tipo deve ser 'CURRICULAR' ou 'EXTRA_CURRICULAR'.");
        }

        if (empresaId == null || empresaId.isBlank()) {
            throw new IllegalArgumentException("A empresa é obrigatória.");
        }
        if (!empresaRepository.existsById(empresaId)) {
            throw new IllegalArgumentException("A empresa indicada não existe.");
        }

        if (representanteId == null || representanteId.isBlank()) {
            throw new IllegalArgumentException("O representante da empresa é obrigatório.");
        }
        if (!representanteRepository.existsById(representanteId)) {
            throw new IllegalArgumentException("O representante indicado não existe.");
        }

        if (p.isRemunerado() && p.getValorRemuneracao() <= 0) {
            throw new IllegalArgumentException("Se o estágio for remunerado, o valor deve ser positivo.");
        }
    }
}
