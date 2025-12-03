package com.upt.lp.componente2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.entity.Departamento;
import com.upt.lp.componente2.repository.CoordenadorRepository;
import com.upt.lp.componente2.repository.CursoRepository;
import com.upt.lp.componente2.repository.DepartamentoRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CoordenadorRepository coordenadorRepository;

    public CursoService(CursoRepository cursoRepository,
                        DepartamentoRepository departamentoRepository,
                        CoordenadorRepository coordenadorRepository) {
        this.cursoRepository = cursoRepository;
        this.departamentoRepository = departamentoRepository;
        this.coordenadorRepository = coordenadorRepository;
    }

    // CREATE
    public Curso createCurso(Curso c, String departamentoId, String coordenadorId) {
        validarDadosCurso(c, departamentoId, coordenadorId, null);

        Departamento dep = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));

        c.setDepartamento(dep);

        if (coordenadorId != null && !coordenadorId.isBlank()) {
            Coordenador coord = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));
            c.setCoordenador(coord);
        } else {
            c.setCoordenador(null);
        }

        return cursoRepository.save(c);
    }

    // READ todos
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    // READ por id
    public Curso getCursoById(String id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));
    }

    // READ por departamento
    public List<Curso> getCursosByDepartamento(String departamentoId) {
        return cursoRepository.findByDepartamentoId(departamentoId);
    }

    // READ por coordenador
    public List<Curso> getCursosByCoordenador(String coordenadorId) {
        return cursoRepository.findByCoordenadorId(coordenadorId);
    }

    // UPDATE
    public Curso updateCurso(String id, Curso dados, String departamentoId, String coordenadorId) {
        Curso existente = getCursoById(id);

        validarDadosCurso(dados, departamentoId, coordenadorId, id);

        Departamento dep = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));
        existente.setDepartamento(dep);

        if (coordenadorId != null && !coordenadorId.isBlank()) {
            Coordenador coord = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));
            existente.setCoordenador(coord);
        } else {
            existente.setCoordenador(null);
        }

        existente.setNome(dados.getNome());
        existente.setCodigo(dados.getCodigo());
        existente.setDuracaoAnos(dados.getDuracaoAnos());
        existente.setGrau(dados.getGrau());

        return cursoRepository.save(existente);
    }

    // DELETE
    public void deleteCurso(String id) {
        Curso c = getCursoById(id);
        // aqui podes adicionar regras (ex.: não apagar se tiver estudantes)
        cursoRepository.delete(c);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosCurso(Curso c, String departamentoId, String coordenadorId, String idAtual) {
    	
        if (c == null) {
            throw new IllegalArgumentException("Curso não pode ser nulo.");
        }

        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatório.");
        }

        if (c.getCodigo() == null || c.getCodigo().isBlank()) {
            throw new IllegalArgumentException("O código do curso é obrigatório.");
        }

        if (c.getDuracaoAnos() <= 0) {
            throw new IllegalArgumentException("A duração do curso deve ser maior que zero.");
        }

        if (c.getGrau() == null || c.getGrau().isBlank()) {
            throw new IllegalArgumentException("O grau académico é obrigatório.");
        }

        if (departamentoId == null || departamentoId.isBlank()) {
            throw new IllegalArgumentException("O departamento é obrigatório.");
        }
        if (!departamentoRepository.existsById(departamentoId)) {
            throw new IllegalArgumentException("O departamento indicado não existe.");
        }

        // Coordenador é opcional, mas se vier, tem de existir
        if (coordenadorId != null && !coordenadorId.isBlank()
                && !coordenadorRepository.existsById(coordenadorId)) {
            throw new IllegalArgumentException("O coordenador indicado não existe.");
        }

        // Unicidade do código
        Optional<Curso> existenteCodigo = cursoRepository.findByCodigo(c.getCodigo());
        if (existenteCodigo.isPresent()
                && (idAtual == null || !existenteCodigo.get().getId().equals(idAtual))) {
            throw new IllegalArgumentException("Já existe um curso com esse código.");
        }
    }
}