package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin("*")
public class OfertaEstagioController {

    private final OfertaEstagioService service;

    public OfertaEstagioController(OfertaEstagioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OfertaEstagio>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaEstagio> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<OfertaEstagio> criar(@RequestBody OfertaEstagio oferta) {
        OfertaEstagio criada = service.criar(oferta);
        return ResponseEntity.created(URI.create("/api/ofertas/" + criada.getId()))
                .body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaEstagio> atualizar(@PathVariable UUID id,
                                                   @RequestBody OfertaEstagio dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }

    // AÇÕES DE NEGÓCIO

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<OfertaEstagio> aprovar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.aprovar(id));
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<OfertaEstagio> rejeitar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.rejeitar(id));
    }
}
