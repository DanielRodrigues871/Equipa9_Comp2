package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.coordenador.CoordenadorRequestDTO;
import com.upt.lp.portalestagios.dto.coordenador.CoordenadorResponseDTO;
import com.upt.lp.portalestagios.service.CoordenadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coordenadores")
@CrossOrigin("*")
public class CoordenadorController {

    private final CoordenadorService service;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorResponseDTO> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CoordenadorResponseDTO> criar(@RequestBody CoordenadorRequestDTO dto) {
        CoordenadorResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/coordenadores/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoordenadorResponseDTO> atualizar(@PathVariable UUID id,
                                                            @RequestBody CoordenadorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}

