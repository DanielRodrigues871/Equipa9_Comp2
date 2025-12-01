package com.upt.pt.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.AreaEstagio;
import com.upt.pt.api.repository.AreaEstagioRepository;

@Service
public class AreaEstagioService {

    private final AreaEstagioRepository areaRepository;

    public AreaEstagioService(AreaEstagioRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    // CREATE
    public AreaEstagio createArea(AreaEstagio a) {
        validarDadosArea(a, null);
        return areaRepository.save(a);
    }

    // READ todos
    public List<AreaEstagio> getAllAreas() {
        return areaRepository.findAll();
    }

    // READ por id
    public AreaEstagio getAreaById(String id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Área de estágio não encontrada."));
    }

    // UPDATE
    public AreaEstagio updateArea(String id, AreaEstagio dados) {
        AreaEstagio existente = getAreaById(id);

        validarDadosArea(dados, id);

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());

        return areaRepository.save(existente);
    }

    // DELETE
    public void deleteArea(String id) {
        AreaEstagio a = getAreaById(id);
        areaRepository.delete(a);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosArea(AreaEstagio a, String idAtual) {
        if (a == null) {
            throw new IllegalArgumentException("Área de estágio não pode ser nula.");
        }

        if (a.getNome() == null || a.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da área de estágio é obrigatório.");
        }

        Optional<AreaEstagio> existenteNome = areaRepository.findByNome(a.getNome());
        if (existenteNome.isPresent()
                && (idAtual == null || !existenteNome.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe uma área de estágio com esse nome.");
        }
    }
}
