package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.area.AreaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.area.AreaEstagioResponseDTO;
import com.upt.lp.portalestagios.service.AreaEstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin("*")
public class AreaEstagioController {

    private final AreaEstagioService service;

    public AreaEstagioController(AreaEstagioService service) {
        this.service = service;
    }

    @GetMapping
    public List<AreaEstagioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AreaEstagioResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<AreaEstagioResponseDTO> criar(@RequestBody AreaEstagioRequestDTO dto) {
        AreaEstagioResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/areas/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public AreaEstagioResponseDTO atualizar(@PathVariable UUID id,
                                            @RequestBody AreaEstagioRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
