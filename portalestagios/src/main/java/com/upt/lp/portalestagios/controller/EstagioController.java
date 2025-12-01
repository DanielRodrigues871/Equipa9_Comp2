package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.estagio.EstagioRequestDTO;
import com.upt.lp.portalestagios.dto.estagio.EstagioResponseDTO;
import com.upt.lp.portalestagios.service.EstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/estagios")
@CrossOrigin("*")
public class EstagioController {

    private final EstagioService service;

    public EstagioController(EstagioService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstagioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public EstagioResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    public EstagioResponseDTO criar(@RequestBody EstagioRequestDTO dto) {
        return service.criar(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}

