package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.proposta.*;
import com.upt.lp.portalestagios.enums.StatusProposta;
import com.upt.lp.portalestagios.service.PropostaEstagioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/propostas")
@CrossOrigin("*")
public class PropostaEstagioController {

    private final PropostaEstagioService service;

    public PropostaEstagioController(PropostaEstagioService service) {
        this.service = service;
    }

    @GetMapping
    public List<PropostaEstagioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PropostaEstagioResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    public PropostaEstagioResponseDTO criar(@RequestBody PropostaEstagioRequestDTO dto) {
        return service.criar(dto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable UUID id,
            @RequestParam StatusProposta status
    ) {
        service.alterarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
