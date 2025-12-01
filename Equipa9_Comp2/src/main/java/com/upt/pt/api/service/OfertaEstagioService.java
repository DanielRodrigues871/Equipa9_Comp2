package com.upt.pt.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.upt.pt.api.entity.*;
import com.upt.pt.api.enums.StatusOferta;
import com.upt.pt.api.enums.TipoEstagio;
import com.upt.pt.api.repository.*;

@Service
public class OfertaEstagioService {

    private final OfertaEstagioRepository ofertaRepository;
    private final EmpresaRepository empresaRepository;
    private final AreaEstagioRepository areaRepository;
    private final CursoRepository cursoRepository;
    private final CoordenadorRepository coordenadorRepository;

    public OfertaEstagioService(OfertaEstagioRepository ofertaRepository,
                                EmpresaRepository empresaRepository,
                                AreaEstagioRepository areaRepository,
                                CursoRepository cursoRepository,
                                CoordenadorRepository coordenadorRepository) {
        this.ofertaRepository = ofertaRepository;
        this.empresaRepository = empresaRepository;
        this.areaRepository = areaRepository;
        this.cursoRepository = cursoRepository;
        this.coordenadorRepository = coordenadorRepository;
    }

    // CREATE
    public OfertaEstagio createOferta(OfertaEstagio o,
                                      String empresaId,
                                      String areaId,
                                      String cursoId,
                                      String coordenadorId) {

        validarDadosOferta(o, empresaId, areaId, cursoId);

        Empresa emp = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));
        o.setEmpresa(emp);

        if (areaId != null && !areaId.isBlank()) {
            AreaEstagio area = areaRepository.findById(areaId)
                    .orElseThrow(() -> new IllegalArgumentException("Área de estágio não encontrada."));
            o.setArea(area);
        }

        if (cursoId != null && !cursoId.isBlank()) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));
            o.setCurso(curso);
        }

        if (coordenadorId != null && !coordenadorId.isBlank()) {
            Coordenador coord = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));
            o.setCoordenadorResponsavel(coord);
        }

        o.setStatus(StatusOferta.PENDENTE);
        o.setDataPublicacao(LocalDateTime.now());

        return ofertaRepository.save(o);
    }

    // READ todos
    public List<OfertaEstagio> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    // READ por id
    public OfertaEstagio getOfertaById(String id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada."));
    }

    // READ por empresa
    public List<OfertaEstagio> getOfertasByEmpresa(String empresaId) {
        return ofertaRepository.findByEmpresaId(empresaId);
    }

    // READ por curso
    public List<OfertaEstagio> getOfertasByCurso(String cursoId) {
        return ofertaRepository.findByCursoId(cursoId);
    }

    // READ por área
    public List<OfertaEstagio> getOfertasByArea(String areaId) {
        return ofertaRepository.findByAreaId(areaId);
    }

    // READ por status
    public List<OfertaEstagio> getOfertasByStatus(StatusOferta status) {
        return ofertaRepository.findByStatus(status);
    }

    // UPDATE
    public OfertaEstagio updateOferta(String id,
                                      OfertaEstagio dados,
                                      String empresaId,
                                      String areaId,
                                      String cursoId,
                                      String coordenadorId) {

        OfertaEstagio existente = getOfertaById(id);

        validarDadosOferta(dados, empresaId, areaId, cursoId);

        Empresa emp = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada."));
        existente.setEmpresa(emp);

        if (areaId != null && !areaId.isBlank()) {
            AreaEstagio area = areaRepository.findById(areaId)
                    .orElseThrow(() -> new IllegalArgumentException("Área de estágio não encontrada."));
            existente.setArea(area);
        } else {
            existente.setArea(null);
        }

        if (cursoId != null && !cursoId.isBlank()) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));
            existente.setCurso(curso);
        } else {
            existente.setCurso(null);
        }

        if (coordenadorId != null && !coordenadorId.isBlank()) {
            Coordenador coord = coordenadorRepository.findById(coordenadorId)
                    .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado."));
            existente.setCoordenadorResponsavel(coord);
        } else {
            existente.setCoordenadorResponsavel(null);
        }

        existente.setTitulo(dados.getTitulo());
        existente.setDescricao(dados.getDescricao());
        existente.setTipo(dados.getTipo());
        existente.setLocalizacao(dados.getLocalizacao());
        existente.setDuracaoMeses(dados.getDuracaoMeses());
        existente.setRequisitos(dados.getRequisitos());
        existente.setDataInicio(dados.getDataInicio());
        existente.setDataFim(dados.getDataFim());
        existente.setDataLimiteInscricao(dados.getDataLimiteInscricao());
        existente.setNumeroVagas(dados.getNumeroVagas());

        return ofertaRepository.save(existente);
    }
    
    // DELETE
    public void deleteOferta(String id) {
        OfertaEstagio o = getOfertaById(id);
        ofertaRepository.delete(o);
    }


 // WORKFLOW
    public OfertaEstagio aprovarOferta(String id) {
        OfertaEstagio o = getOfertaById(id);
        o.setStatus(StatusOferta.APROVADO);
        o.setDataAprovacao(LocalDateTime.now());
        return ofertaRepository.save(o);
    }

    public OfertaEstagio rejeitarOferta(String id) {
        OfertaEstagio o = getOfertaById(id);
        o.setStatus(StatusOferta.REJEITADO);
        return ofertaRepository.save(o);
    }

    public OfertaEstagio encerrarOferta(String id) {
        OfertaEstagio o = getOfertaById(id);
        o.setStatus(StatusOferta.ENCERRADO);
        return ofertaRepository.save(o);
    }

    // =========================
    //   MÉTODO DE VALIDAÇÃO
    // =========================
    private void validarDadosOferta(OfertaEstagio o,
                                    String empresaId,
                                    String areaId,
                                    String cursoId) {
        if (o == null) {
            throw new IllegalArgumentException("Oferta não pode ser nula.");
        }

        if (o.getTitulo() == null || o.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }

        if (o.getDescricao() == null || o.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }

        if (empresaId == null || empresaId.isBlank()) {
            throw new IllegalArgumentException("Empresa é obrigatória.");
        }
        if (!empresaRepository.existsById(empresaId)) {
            throw new IllegalArgumentException("A empresa indicada não existe.");
        }

        TipoEstagio tipo = o.getTipo();
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de estágio é obrigatório.");
        }

        if (o.getDuracaoMeses() <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que 0 meses.");
        }

        if (o.getNumeroVagas() < 1) {
            throw new IllegalArgumentException("Número de vagas deve ser pelo menos 1.");
        }

        if (areaId != null && !areaId.isBlank()
                && !areaRepository.existsById(areaId)) {
            throw new IllegalArgumentException("A área de estágio indicada não existe.");
        }

        if (cursoId != null && !cursoId.isBlank()
                && !cursoRepository.existsById(cursoId)) {
            throw new IllegalArgumentException("O curso indicado não existe.");
        }
    }
}
