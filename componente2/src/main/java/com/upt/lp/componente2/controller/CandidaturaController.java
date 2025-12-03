package com.upt.lp.componente2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.componente2.dto.CandidaturaDTO;
import com.upt.lp.componente2.entity.Candidatura;
import com.upt.lp.componente2.mapper.CandidaturaMapper;
import com.upt.lp.componente2.service.CandidaturaService;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    // CREATE
    // POST /api/candidaturas?estudanteId=XXX&ofertaId=YYY
    @PostMapping
    public ResponseEntity<CandidaturaDTO> create(@RequestBody CandidaturaDTO dto, @RequestParam String estudanteId, @RequestParam String ofertaId) {

        Candidatura entidade = CandidaturaMapper.toEntity(dto);
        Candidatura criada =
                candidaturaService.createCandidatura(entidade, estudanteId, ofertaId);

        CandidaturaDTO resposta = CandidaturaMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/candidaturas
    @GetMapping
    public List<CandidaturaDTO> getAll() {
        return candidaturaService.getAllCandidaturas()
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/candidaturas/{id}
    @GetMapping("/{id}")
    public CandidaturaDTO getById(@PathVariable String id) {
        Candidatura c = candidaturaService.getCandidaturaById(id);
        return CandidaturaMapper.toDTO(c);
    }

    // READ por estudante
    // GET /api/candidaturas/estudante/{estudanteId}
    @GetMapping("/estudante/{estudanteId}")
    public List<CandidaturaDTO> getByEstudante(@PathVariable String estudanteId) {
        return candidaturaService.getCandidaturasByEstudante(estudanteId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // READ por oferta
    // GET /api/candidaturas/oferta/{ofertaId}
    @GetMapping("/oferta/{ofertaId}")
    public List<CandidaturaDTO> getByOferta(@PathVariable String ofertaId) {
        return candidaturaService.getCandidaturasByOferta(ofertaId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // UPDATE (carta/observações)
    // PUT /api/candidaturas/{id}
    @PutMapping("/{id}")
    public CandidaturaDTO update(@PathVariable String id, @RequestBody CandidaturaDTO dto) {

        Candidatura dados = CandidaturaMapper.toEntity(dto);
        Candidatura atualizada = candidaturaService.updateCandidatura(id, dados);
        return CandidaturaMapper.toDTO(atualizada);
    }

    // WORKFLOW: colocar em análise
    // POST /api/candidaturas/{id}/analise?coordenadorId=ZZZ
    @PostMapping("/{id}/analise")
    public CandidaturaDTO colocarEmAnalise(@PathVariable String id, @RequestParam String coordenadorId) {

        Candidatura c = candidaturaService.colocarEmAnalise(id, coordenadorId);
        return CandidaturaMapper.toDTO(c);
    }

    // WORKFLOW: aprovar
    // POST /api/candidaturas/{id}/aprovar?coordenadorId=ZZZ
    @PostMapping("/{id}/aprovar")
    public CandidaturaDTO aprovar(@PathVariable String id, @RequestParam String coordenadorId, @RequestBody(required = false) CandidaturaDTO dto) {

        String observacoes = dto != null ? dto.getObservacoes() : null;
        Candidatura c = candidaturaService.aprovar(id, coordenadorId, observacoes);
        return CandidaturaMapper.toDTO(c);
    }

    // WORKFLOW: rejeitar
    // POST /api/candidaturas/{id}/rejeitar?coordenadorId=ZZZ
    @PostMapping("/{id}/rejeitar")
    public CandidaturaDTO rejeitar(@PathVariable String id, @RequestParam String coordenadorId, @RequestBody CandidaturaDTO dto) {

        Candidatura c = candidaturaService.rejeitar(id, coordenadorId, dto.getObservacoes());
        return CandidaturaMapper.toDTO(c);
    }

    // DELETE
    // DELETE /api/candidaturas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        candidaturaService.deleteCandidatura(id);
        return ResponseEntity.noContent().build();
    }
}