package com.upt.pt.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.entity.Departamento;
import com.upt.pt.api.repository.CoordenadorRepository;
import com.upt.pt.api.repository.DepartamentoRepository;
import com.upt.pt.api.security.PasswordUtil;

@Service
public class CoordenadorService {

    private final CoordenadorRepository coordenadorRepository;
    private final DepartamentoRepository departamentoRepository;

    public CoordenadorService(CoordenadorRepository coordenadorRepository,
                              DepartamentoRepository departamentoRepository) {
        this.coordenadorRepository = coordenadorRepository;
        this.departamentoRepository = departamentoRepository;
    }

    // CREATE
    public Coordenador createCoordenador(Coordenador c, String departamentoId) {
        validarDadosCoordenador(c, departamentoId, null);

        Departamento departamento = departamentoRepository.findById(departamentoId).get();
        c.setDepartamento(departamento);

        return coordenadorRepository.save(c);
    }

    // READ todos
    public List<Coordenador> getAllCoordenadores() {
        return coordenadorRepository.findAll();
    }

    // READ por id
    public Coordenador getCoordenadorById(String id) {
        return coordenadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));
    }

    // UPDATE
    public Coordenador updateCoordenador(String id, Coordenador dados, String departamentoId) {
        Coordenador existente = getCoordenadorById(id);

        validarDadosCoordenador(dados, departamentoId, id);

        Departamento departamento = departamentoRepository.findById(departamentoId).get();

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setPassword(dados.getPassword());
        existente.setDepartamento(departamento);

        return coordenadorRepository.save(existente);
    }

    // DELETE
    public void deleteCoordenador(String id) {
        Coordenador c = getCoordenadorById(id);
        // aqui podes pôr regra: não apagar se tiver cursosGeridos, etc.
        coordenadorRepository.delete(c);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosCoordenador(Coordenador c, String departamentoId, String idAtual) {
        if (c == null) {
            throw new IllegalArgumentException("Coordenador não pode ser nulo.");
        }

        // Nome
        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        // Email
        if (c.getEmail() == null || c.getEmail().isBlank() || !c.getEmail().contains("@")) {
            throw new IllegalArgumentException("O email é obrigatório e deve ser válido.");
        }

        // Password 
        PasswordUtil.validarPasswordForte(c.getPassword());
        
        // Departamento obrigatório e existente
        if (departamentoId == null || departamentoId.isBlank()) {
            throw new IllegalArgumentException("O departamento é obrigatório.");
        }
        if (!departamentoRepository.existsById(departamentoId)) {
            throw new IllegalArgumentException("O departamento indicado não existe.");
        }

        // Unicidade do email (entre coordenadores; se quiseres global em Utilizador,
        // usarias um UtilizadorRepository em vez deste)
        Optional<Coordenador> existenteEmail = coordenadorRepository.findByEmail(c.getEmail());
        if (existenteEmail.isPresent()
                && (idAtual == null || !existenteEmail.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um coordenador com esse email.");
        }
    }
}
