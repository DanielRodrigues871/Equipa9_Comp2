package com.upt.pt.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.CandidaturaDTO;
import com.upt.pt.api.entity.Candidatura;
import com.upt.pt.api.mapper.CandidaturaMapper;
import com.upt.pt.api.service.CandidaturaService;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    // CREATE (CORRIGIDO)
    // POST /api/candidaturas?estudanteId=XXX&ofertaId=YYY
    // Body: { "cartaMotivacao": "..." }
    @PostMapping
    public ResponseEntity<CandidaturaDTO> create(
            @RequestParam String estudanteId, 
            @RequestParam String ofertaId,
            @RequestBody Map<String, String> body) {

        // Extrai a carta do JSON simples
        String carta = body.getOrDefault("cartaMotivacao", "");

        // Chama o serviço passando os IDs e a carta
        Candidatura criada = candidaturaService.criarCandidatura(estudanteId, ofertaId, carta);

        CandidaturaDTO resposta = CandidaturaMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    @GetMapping
    public List<CandidaturaDTO> getAll() {
        return candidaturaService.getAllCandidaturas()
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // READ por id
    @GetMapping("/{id}")
    public CandidaturaDTO getById(@PathVariable String id) {
        Candidatura c = candidaturaService.getCandidaturaById(id);
        return CandidaturaMapper.toDTO(c);
    }

    // READ por estudante
    @GetMapping("/estudante/{estudanteId}")
    public List<CandidaturaDTO> getByEstudante(@PathVariable String estudanteId) {
        // Se o seu serviço se chamar 'listarPorEstudante', ajuste aqui. 
        // Vou assumir 'getCandidaturasByEstudante' baseado no seu código anterior.
        return candidaturaService.getCandidaturasByEstudante(estudanteId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // READ por oferta
    @GetMapping("/oferta/{ofertaId}")
    public List<CandidaturaDTO> getByOferta(@PathVariable String ofertaId) {
        return candidaturaService.getCandidaturasByOferta(ofertaId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .toList();
    }

    // UPDATE (carta/observações)
    @PutMapping("/{id}")
    public CandidaturaDTO update(@PathVariable String id, @RequestBody CandidaturaDTO dto) {
        Candidatura dados = CandidaturaMapper.toEntity(dto);
        Candidatura atualizada = candidaturaService.updateCandidatura(id, dados);
        return CandidaturaMapper.toDTO(atualizada);
    }

    // WORKFLOW: colocar em análise
    @PostMapping("/{id}/analise")
    public CandidaturaDTO colocarEmAnalise(@PathVariable String id, @RequestParam String coordenadorId) {
        Candidatura c = candidaturaService.colocarEmAnalise(id, coordenadorId);
        return CandidaturaMapper.toDTO(c);
    }

    // WORKFLOW: aprovar
    @PostMapping("/{id}/aprovar")
    public CandidaturaDTO aprovar(@PathVariable String id, @RequestParam String coordenadorId, @RequestBody(required = false) CandidaturaDTO dto) {
        String observacoes = dto != null ? dto.getObservacoes() : null;
        Candidatura c = candidaturaService.aprovar(id, coordenadorId, observacoes);
        return CandidaturaMapper.toDTO(c);
    }

    // WORKFLOW: rejeitar
    @PostMapping("/{id}/rejeitar")
    public CandidaturaDTO rejeitar(@PathVariable String id, @RequestParam String coordenadorId, @RequestBody CandidaturaDTO dto) {
        Candidatura c = candidaturaService.rejeitar(id, coordenadorId, dto.getObservacoes());
        return CandidaturaMapper.toDTO(c);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        candidaturaService.deleteCandidatura(id);
        return ResponseEntity.noContent().build();
    }
}