package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.candidatura.CandidaturaRequestDTO;
import com.upt.lp.portalestagios.dto.candidatura.CandidaturaResponseDTO;
import com.upt.lp.portalestagios.service.CandidaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin("*")
public class CandidaturaController {

    private final CandidaturaService service;

    public CandidaturaController(CandidaturaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CandidaturaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CandidaturaResponseDTO> criar(@RequestBody CandidaturaRequestDTO dto) {
        CandidaturaResponseDTO criada = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/candidaturas/" + criada.getId()))
                .body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidaturaResponseDTO> atualizar(@PathVariable UUID id,
                                                            @RequestBody CandidaturaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }

    // ações de negócio
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<CandidaturaResponseDTO> aprovar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.aprovar(id));
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<CandidaturaResponseDTO> rejeitar(@PathVariable UUID id,
                                                           @RequestParam(required = false) String motivo) {
        return ResponseEntity.ok(service.rejeitar(id, motivo));
    }

    @PutMapping("/{id}/analise")
    public ResponseEntity<CandidaturaResponseDTO> colocarEmAnalise(@PathVariable UUID id) {
        return ResponseEntity.ok(service.colocarEmAnalise(id));
    }
}
