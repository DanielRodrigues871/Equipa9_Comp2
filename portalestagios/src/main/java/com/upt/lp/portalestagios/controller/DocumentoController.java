package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Documento;
import com.upt.lp.portalestagios.service.DocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin("*")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Documento> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> buscar(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estagio/{estagioId}")
    public List<Documento> listarPorEstagio(@PathVariable UUID estagioId) {
        return service.findByEstagio(estagioId);
    }

    @PostMapping
    public Documento criar(@RequestBody Documento doc) {
        return service.save(doc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
