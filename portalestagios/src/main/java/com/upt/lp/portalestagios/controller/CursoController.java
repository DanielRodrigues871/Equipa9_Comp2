package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Curso;
import com.upt.lp.portalestagios.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Curso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping("/{departamentoId}")
    public Curso criar(@PathVariable String departamentoId,
                       @RequestBody Curso c) {
        return service.criar(departamentoId, c);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable String id, @RequestBody Curso c) {
        return service.atualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}

