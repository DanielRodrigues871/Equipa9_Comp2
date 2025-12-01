package com.upt.pt.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.EstagioDTO;
import com.upt.pt.api.entity.Estagio;
import com.upt.pt.api.mapper.EstagioMapper;
import com.upt.pt.api.service.EstagioService;

@RestController
@RequestMapping("/api/estagios")
public class EstagioController {

    private final EstagioService estagioService;

    public EstagioController(EstagioService estagioService) {
        this.estagioService = estagioService;
    }

    // CREATE
    // POST /api/estagios?estudanteId=E&ofertaId=O
    @PostMapping
    public ResponseEntity<EstagioDTO> create(@RequestBody EstagioDTO dto,
                                             @RequestParam String estudanteId,
                                             @RequestParam String ofertaId) {

        Estagio entidade = EstagioMapper.toEntity(dto);
        Estagio criado = estagioService.createEstagio(entidade, estudanteId, ofertaId);
        EstagioDTO resposta = EstagioMapper.toDTO(criado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/estagios
    @GetMapping
    public List<EstagioDTO> getAll() {
        return estagioService.getAllEstagios()
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/estagios/{id}
    @GetMapping("/{id}")
    public EstagioDTO getById(@PathVariable String id) {
        Estagio e = estagioService.getEstagioById(id);
        return EstagioMapper.toDTO(e);
    }

    // READ por estudante
    // GET /api/estagios/estudante/{estudanteId}
    @GetMapping("/estudante/{estudanteId}")
    public List<EstagioDTO> getByEstudante(@PathVariable String estudanteId) {
        return estagioService.getEstagiosByEstudante(estudanteId)
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    // READ por empresa
    // GET /api/estagios/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public List<EstagioDTO> getByEmpresa(@PathVariable String empresaId) {
        return estagioService.getEstagiosByEmpresa(empresaId)
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    // READ por curso
    // GET /api/estagios/curso/{cursoId}
    @GetMapping("/curso/{cursoId}")
    public List<EstagioDTO> getByCurso(@PathVariable String cursoId) {
        return estagioService.getEstagiosByCurso(cursoId)
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    // READ por estado
    // GET /api/estagios/estado/{estado}
    @GetMapping("/estado/{estado}")
    public List<EstagioDTO> getByEstado(@PathVariable String estado) {
        return estagioService.getEstagiosByEstado(estado)
                .stream()
                .map(EstagioMapper::toDTO)
                .toList();
    }

    // UPDATE (dados base)
    // PUT /api/estagios/{id}
    @PutMapping("/{id}")
    public EstagioDTO update(@PathVariable String id,
                             @RequestBody EstagioDTO dto) {
        Estagio dados = EstagioMapper.toEntity(dto);
        Estagio atualizado = estagioService.updateEstagio(id, dados);
        return EstagioMapper.toDTO(atualizado);
    }

    // WORKFLOW: concluir
    // POST /api/estagios/{id}/concluir
    @PostMapping("/{id}/concluir")
    public EstagioDTO concluir(@PathVariable String id,
                               @RequestBody EstagioDTO dto) {
        LocalDate dataFim = dto.getDataFim();
        String notaFinal = dto.getNotaFinal();
        Estagio e = estagioService.concluirEstagio(id, notaFinal, dataFim);
        return EstagioMapper.toDTO(e);
    }

    // WORKFLOW: cancelar
    // POST /api/estagios/{id}/cancelar
    @PostMapping("/{id}/cancelar")
    public EstagioDTO cancelar(@PathVariable String id,
                               @RequestBody EstagioDTO dto) {
        Estagio e = estagioService.cancelarEstagio(id, dto.getObservacoes());
        return EstagioMapper.toDTO(e);
    }

    // DELETE
    // DELETE /api/estagios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        estagioService.deleteEstagio(id);
        return ResponseEntity.noContent().build();
    }
}
