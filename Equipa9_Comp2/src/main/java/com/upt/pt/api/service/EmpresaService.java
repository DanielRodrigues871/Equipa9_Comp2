package com.upt.pt.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.Empresa;
import com.upt.pt.api.repository.EmpresaRepository;
import com.upt.pt.api.security.NifUtils;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // CREATE
    public Empresa createEmpresa(Empresa e) {
        validarDadosEmpresa(e, null);
        e.setDataCriacao(LocalDateTime.now());
        e.setAtiva(true);
        return empresaRepository.save(e);
    }

    // READ todos
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    // READ por id
    public Empresa getEmpresaById(String id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));
    }

    // UPDATE
    public Empresa updateEmpresa(String id, Empresa dados) {
        Empresa existente = getEmpresaById(id);

        validarDadosEmpresa(dados, id);

        existente.setNome(dados.getNome());
        existente.setNif(dados.getNif());
        existente.setMorada(dados.getMorada());
        existente.setTelefone(dados.getTelefone());
        existente.setEmail(dados.getEmail());
        existente.setWebsite(dados.getWebsite());
        existente.setDescricao(dados.getDescricao());
        existente.setAtiva(dados.isAtiva());

        return empresaRepository.save(existente);
    }

    // ATIVAR / DESATIVAR
    public Empresa ativarEmpresa(String id) {
        Empresa e = getEmpresaById(id);
        e.setAtiva(true);
        return empresaRepository.save(e);
    }

    public Empresa desativarEmpresa(String id) {
        Empresa e = getEmpresaById(id);
        e.setAtiva(false);
        return empresaRepository.save(e);
    }

    // DELETE
    public void deleteEmpresa(String id) {
        Empresa e = getEmpresaById(id);
        empresaRepository.delete(e);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosEmpresa(Empresa e, String idAtual) {
        if (e == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula.");
        }

        if (e.getNome() == null || e.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da empresa é obrigatório.");
        }

        String nif = e.getNif();
        if (nif == null || nif.isBlank()) {
            throw new IllegalArgumentException("O NIF é obrigatório.");
        }
        if (!NifUtils.isNifValido(nif)) {
            throw new IllegalArgumentException("NIF inválido.");
        }

        String email = e.getEmail();
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        if (!email.contains("@") || !email.matches(".*\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException(
                    "Email inválido. Deve conter '@' e terminar com um domínio (.pt, .com, etc.).");
        }

        Optional<Empresa> existenteNif = empresaRepository.findByNif(nif);
        if (existenteNif.isPresent()
                && (idAtual == null || !existenteNif.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe uma empresa com esse NIF.");
        }

        Optional<Empresa> existenteEmail = empresaRepository.findByEmail(email);
        if (existenteEmail.isPresent()
                && (idAtual == null || !existenteEmail.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe uma empresa com esse email.");
        }
    }
}
