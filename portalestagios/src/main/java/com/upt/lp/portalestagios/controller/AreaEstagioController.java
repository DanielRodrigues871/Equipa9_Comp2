package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import com.upt.lp.portalestagios.service.AreaEstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin("*")
public class AreaEstagioController {

    private final AreaEstagioService areaService;

    public AreaEstagioController(AreaEstagioService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<List<AreaEstagio>> listarTodos() {
        return ResponseEntity.ok(areaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaEstagio> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(areaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AreaEstagio> criar(@RequestBody AreaEstagio area) {
        return ResponseEntity.ok(areaService.criar(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaEstagio> atualizar(
            @PathVariable String id,
            @RequestBody AreaEstagio novaArea
    ) {
        return ResponseEntity.ok(areaService.atualizar(id, novaArea));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        areaService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
