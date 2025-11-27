package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.entity.Empresa;
import com.upt.lp.portalestagios.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Empresa> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Empresa buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping
    public Empresa criar(@RequestBody Empresa empresa) {
        return service.criar(empresa);
    }

    @PutMapping("/{id}")
    public Empresa atualizar(@PathVariable String id, @RequestBody Empresa empresa) {
        return service.atualizar(id, empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
