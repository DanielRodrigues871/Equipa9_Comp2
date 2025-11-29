package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.curso.CursoRequestDTO;
import com.upt.lp.portalestagios.dto.curso.CursoResponseDTO;
import com.upt.lp.portalestagios.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin("*")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CursoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CursoResponseDTO buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criar(@RequestBody CursoRequestDTO dto) {
        CursoResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/cursos/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public CursoResponseDTO atualizar(@PathVariable String id,
                                      @RequestBody CursoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
