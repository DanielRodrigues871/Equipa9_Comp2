package com.upt.pt.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.Departamento;
import com.upt.pt.api.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    // CREATE
    public Departamento createDepartamento(Departamento d) {
        validarDadosDepartamento(d, null);
        return departamentoRepository.save(d);
    }

    // READ todos
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    // READ por id
    public Departamento getDepartamentoById(String id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));
    }

    // UPDATE
    public Departamento updateDepartamento(String id, Departamento dados) {
        Departamento existente = getDepartamentoById(id);

        validarDadosDepartamento(dados, id);

        existente.setNome(dados.getNome());
        existente.setCodigo(dados.getCodigo());
        existente.setDescricao(dados.getDescricao());

        return departamentoRepository.save(existente);
    }

    // DELETE
    public void deleteDepartamento(String id) {
        Departamento d = getDepartamentoById(id);
        // podes adicionar regra: não apagar se tiver cursos, etc.
        departamentoRepository.delete(d);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosDepartamento(Departamento d, String idAtual) {
        if (d == null) {
            throw new IllegalArgumentException("Departamento não pode ser nulo.");
        }

        if (d.getNome() == null || d.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do departamento é obrigatório.");
        }

        if (d.getCodigo() == null || d.getCodigo().isBlank()) {
            throw new IllegalArgumentException("O código do departamento é obrigatório.");
        }

        // Unicidade do código
        Optional<Departamento> existenteCodigo = departamentoRepository.findByCodigo(d.getCodigo());
        if (existenteCodigo.isPresent()
                && (idAtual == null || !existenteCodigo.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um departamento com esse código.");
        }
    }
}
