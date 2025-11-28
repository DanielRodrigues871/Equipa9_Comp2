package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin("*")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscar(@PathVariable String id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa empresa) {
        Empresa criada = service.criar(empresa);
        return ResponseEntity.created(URI.create("/api/empresas/" + criada.getId()))
                .body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable String id, @RequestBody Empresa empresa) {
        return ResponseEntity.ok(service.atualizar(id, empresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
