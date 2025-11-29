package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.departamento.DepartamentoRequestDTO;
import com.upt.lp.portalestagios.dto.departamento.DepartamentoResponseDTO;
import com.upt.lp.portalestagios.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin("*")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<DepartamentoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public DepartamentoResponseDTO buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DepartamentoResponseDTO> criar(@RequestBody DepartamentoRequestDTO dto) {
        DepartamentoResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/departamentos/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public DepartamentoResponseDTO atualizar(@PathVariable String id,
                                             @RequestBody DepartamentoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
