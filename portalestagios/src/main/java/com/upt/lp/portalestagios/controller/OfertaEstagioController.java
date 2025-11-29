package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioRequestDTO;
import com.upt.lp.portalestagios.dto.oferta.OfertaEstagioResponseDTO;
import com.upt.lp.portalestagios.service.OfertaEstagioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin("*")
public class OfertaEstagioController {

    private final OfertaEstagioService service;

    public OfertaEstagioController(OfertaEstagioService service) {
        this.service = service;
    }

    @GetMapping
    public List<OfertaEstagioResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public OfertaEstagioResponseDTO buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<OfertaEstagioResponseDTO> criar(@RequestBody OfertaEstagioRequestDTO dto) {
        OfertaEstagioResponseDTO criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/api/ofertas/" + criado.getId()))
                .body(criado);
    }

    @PutMapping("/{id}")
    public OfertaEstagioResponseDTO atualizar(@PathVariable String id,
                                              @RequestBody OfertaEstagioRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PutMapping("/{id}/aprovar")
    public OfertaEstagioResponseDTO aprovar(@PathVariable String id) {
        return service.aprovar(id);
    }

    @PutMapping("/{id}/rejeitar")
    public OfertaEstagioResponseDTO rejeitar(@PathVariable String id) {
        return service.rejeitar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
