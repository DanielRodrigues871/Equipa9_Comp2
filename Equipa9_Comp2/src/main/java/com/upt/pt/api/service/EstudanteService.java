package com.upt.pt.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.Curso;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.repository.CursoRepository;
import com.upt.pt.api.repository.EstudanteRepository;
import com.upt.pt.api.security.PasswordUtil;

@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;

    public EstudanteService(EstudanteRepository estudanteRepository,
                            CursoRepository cursoRepository) {
        this.estudanteRepository = estudanteRepository;
        this.cursoRepository = cursoRepository;
    }

    // CREATE
    public Estudante createEstudante(Estudante e, String cursoId) {
        validarDadosEstudante(e, cursoId, null);

        // curso já foi validado em validarDadosEstudante
        Curso curso = cursoRepository.findById(cursoId).get();
        e.setCurso(curso);

        return estudanteRepository.save(e);
    }

    // READ todos
    public List<Estudante> getAllEstudantes() {
        return estudanteRepository.findAll();
    }

    // READ por id
    public Estudante getEstudanteById(String id) {
        return estudanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado."));
    }

    // UPDATE
    public Estudante updateEstudante(String id, Estudante dados, String cursoId) {
        Estudante existente = getEstudanteById(id);

        validarDadosEstudante(dados, cursoId, id);

        Curso curso = cursoRepository.findById(cursoId).get();

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setPassword(dados.getPassword());
        existente.setNumeroEstudante(dados.getNumeroEstudante());
        existente.setAnoMatricula(dados.getAnoMatricula());
        existente.setMedia(dados.getMedia());
        existente.setCurso(curso);
        existente.setCompetencias(dados.getCompetencias());

        return estudanteRepository.save(existente);
    }

    // DELETE
    public void deleteEstudante(String id) {
        Estudante e = getEstudanteById(id);
        // aqui podes adicionar regras (ex.: não apagar se tiver candidaturas ativas)
        estudanteRepository.delete(e);
    }

    // Estudantes com média > 9.5
    public List<Estudante> getEstudantesComMediaMaiorQue9_5() {
        return estudanteRepository.findByMediaGreaterThanEqual(9.5);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosEstudante(Estudante e, String cursoId, String idAtual) {
        if (e == null) {
            throw new IllegalArgumentException("Estudante não pode ser nulo.");
        }

        // Nome
        if (e.getNome() == null || e.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        // Email (formato simples)
        if (e.getEmail() == null || e.getEmail().isBlank()
                || !e.getEmail().contains("@")) {
            throw new IllegalArgumentException("O email é obrigatório e deve ser válido.");
        }

        // Password
        PasswordUtil.validarPasswordForte(e.getPassword());

        // Número de estudante: exatamente 5 dígitos
        if (e.getNumeroEstudante() == null || !e.getNumeroEstudante().matches("\\d{5}")) {
            throw new IllegalArgumentException("O número de estudante deve ter exatamente 5 dígitos numéricos.");
        }

        // Ano de matrícula >= 1
        if (e.getAnoMatricula() < 1) {
            throw new IllegalArgumentException("O ano de matrícula deve ser maior ou igual a 1.");
        }

        // Média entre 0 e 20
        if (e.getMedia() < 0 || e.getMedia() > 20) {
            throw new IllegalArgumentException("A média deve estar entre 0 e 20.");
        }

        // Curso obrigatório e existente (id é String)
        if (cursoId == null || cursoId.isBlank()) {
            throw new IllegalArgumentException("O curso é obrigatório.");
        }
        if (!cursoRepository.existsById(cursoId)) {
            throw new IllegalArgumentException("O curso indicado não existe.");
        }

        // Unicidade do email
        Optional<Estudante> existenteEmail = estudanteRepository.findByEmail(e.getEmail());
        if (existenteEmail.isPresent()
                && (idAtual == null || !existenteEmail.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um estudante com esse email.");
        }

        // Unicidade do número de estudante
        Optional<Estudante> existenteNumero = estudanteRepository.findByNumeroEstudante(e.getNumeroEstudante());
        if (existenteNumero.isPresent()
                && (idAtual == null || !existenteNumero.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um estudante com esse número de estudante.");
        }
    }
}
