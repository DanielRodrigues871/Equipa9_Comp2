package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Departamento;
import com.upt.lp.portalestagios.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Departamento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Departamento buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping
    public Departamento criar(@RequestBody Departamento d) {
        return service.criar(d);
    }

    @PutMapping("/{id}")
    public Departamento atualizar(@PathVariable String id, @RequestBody Departamento d) {
        return service.atualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
