package com.upt.lp.componente2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.componente2.dto.PropostaEstagioDTO;
import com.upt.lp.componente2.entity.PropostaEstagio;
import com.upt.lp.componente2.mapper.PropostaEstagioMapper;
import com.upt.lp.componente2.service.PropostaEstagioService;

@RestController
@RequestMapping("/api/propostas")
public class PropostaEstagioController {

    private final PropostaEstagioService propostaService;

    public PropostaEstagioController(PropostaEstagioService propostaService) {
        this.propostaService = propostaService;
    }

    // CREATE
    // POST /api/propostas?empresaId=E&representanteId=R
    @PostMapping
    public ResponseEntity<PropostaEstagioDTO> create(@RequestBody PropostaEstagioDTO dto,
                                                     @RequestParam String empresaId,
                                                     @RequestParam String representanteId) {

        PropostaEstagio entidade = PropostaEstagioMapper.toEntity(dto);
        PropostaEstagio criada =
                propostaService.createProposta(entidade, empresaId, representanteId, dto.getAreasIds());

        PropostaEstagioDTO resposta = PropostaEstagioMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    @GetMapping
    public List<PropostaEstagioDTO> getAll() {
        return propostaService.getAllPropostas()
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .toList();
    }

    // READ por id
    @GetMapping("/{id}")
    public PropostaEstagioDTO getById(@PathVariable Long id) {
        PropostaEstagio p = propostaService.getPropostaById(id);
        return PropostaEstagioMapper.toDTO(p);
    }

    // READ por empresa
    @GetMapping("/empresa/{empresaId}")
    public List<PropostaEstagioDTO> getByEmpresa(@PathVariable String empresaId) {
        return propostaService.getPropostasByEmpresa(empresaId)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .toList();
    }

    // READ por representante
    @GetMapping("/representante/{representanteId}")
    public List<PropostaEstagioDTO> getByRepresentante(@PathVariable String representanteId) {
        return propostaService.getPropostasByRepresentante(representanteId)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .toList();
    }

    // READ por status
    @GetMapping("/status/{status}")
    public List<PropostaEstagioDTO> getByStatus(@PathVariable String status) {
        return propostaService.getPropostasByStatus(status)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .toList();
    }

    // UPDATE
    @PutMapping("/{id}")
    public PropostaEstagioDTO update(@PathVariable Long id,
                                     @RequestBody PropostaEstagioDTO dto,
                                     @RequestParam String empresaId,
                                     @RequestParam String representanteId) {

        PropostaEstagio dados = PropostaEstagioMapper.toEntity(dto);
        PropostaEstagio atualizada =
                propostaService.updateProposta(id, dados, empresaId, representanteId, dto.getAreasIds());

        return PropostaEstagioMapper.toDTO(atualizada);
    }

    // WORKFLOW: aprovar
    @PostMapping("/{id}/aprovar")
    public PropostaEstagioDTO aprovar(@PathVariable Long id) {
        PropostaEstagio p = propostaService.aprovarProposta(id);
        return PropostaEstagioMapper.toDTO(p);
    }

    // WORKFLOW: rejeitar
    @PostMapping("/{id}/rejeitar")
    public PropostaEstagioDTO rejeitar(@PathVariable Long id) {
        PropostaEstagio p = propostaService.rejeitarProposta(id);
        return PropostaEstagioMapper.toDTO(p);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propostaService.deleteProposta(id);
        return ResponseEntity.noContent().build();
    }
}