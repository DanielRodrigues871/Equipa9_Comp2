package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.service.CandidaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin("*")
public class CandidaturaController {

    private final CandidaturaService service;

    public CandidaturaController(CandidaturaService service) {
        this.service = service;
    }

    // ---------------------- LISTAR ----------------------
    @GetMapping
    public ResponseEntity<List<Candidatura>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // ---------------------- BUSCAR POR ID ----------------------
    @GetMapping("/{id}")
    public ResponseEntity<Candidatura> buscar(@PathVariable String id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    // ---------------------- CRIAR ----------------------
    @PostMapping
    public ResponseEntity<Candidatura> criar(@RequestBody Candidatura candidatura) {
        Candidatura criada = service.criar(candidatura);
        return ResponseEntity.created(URI.create("/api/candidaturas/" + criada.getId()))
                .body(criada);
    }

    // ---------------------- ATUALIZAR ----------------------
    @PutMapping("/{id}")
    public ResponseEntity<Candidatura> atualizar(
            @PathVariable String id,
            @RequestBody Candidatura candidatura
    ) {
        return ResponseEntity.ok(service.atualizar(id, candidatura));
    }

    // ---------------------- APAGAR ----------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }

    // ======================================================
    //              AÇÕES DE NEGÓCIO DA CANDIDATURA
    // ======================================================

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<Candidatura> aprovar(@PathVariable String id) {
        return ResponseEntity.ok(service.aprovar(id));
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<Candidatura> rejeitar(
            @PathVariable String id,
            @RequestParam(required = false) String motivo
    ) {
        return ResponseEntity.ok(service.rejeitar(id, motivo));
    }

    @PutMapping("/{id}/analise")
    public ResponseEntity<Candidatura> colocarEmAnalise(@PathVariable String id) {
        return ResponseEntity.ok(service.colocarEmAnalise(id));
    }
}
