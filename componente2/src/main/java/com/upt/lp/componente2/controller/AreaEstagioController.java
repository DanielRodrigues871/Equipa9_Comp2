package com.upt.lp.componente2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.componente2.dto.AreaEstagioDTO;
import com.upt.lp.componente2.entity.AreaEstagio;
import com.upt.lp.componente2.mapper.AreaEstagioMapper;
import com.upt.lp.componente2.service.AreaEstagioService;

@RestController
@RequestMapping("/api/areas")
public class AreaEstagioController {

    private final AreaEstagioService areaService;

    public AreaEstagioController(AreaEstagioService areaService) {
        this.areaService = areaService;
    }

    // CREATE
    // POST /api/areas
    @PostMapping
    public ResponseEntity<AreaEstagioDTO> create(@RequestBody AreaEstagioDTO dto) {
        AreaEstagio entidade = AreaEstagioMapper.toEntity(dto);
        AreaEstagio criada = areaService.createArea(entidade);
        AreaEstagioDTO resposta = AreaEstagioMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/areas
    @GetMapping
    public List<AreaEstagioDTO> getAll() {
        return areaService.getAllAreas()
                .stream()
                .map(AreaEstagioMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/areas/{id}
    @GetMapping("/{id}")
    public AreaEstagioDTO getById(@PathVariable String id) {
        AreaEstagio a = areaService.getAreaById(id);
        return AreaEstagioMapper.toDTO(a);
    }

    // UPDATE
    // PUT /api/areas/{id}
    @PutMapping("/{id}")
    public AreaEstagioDTO update(@PathVariable String id,
                                 @RequestBody AreaEstagioDTO dto) {
        AreaEstagio dados = AreaEstagioMapper.toEntity(dto);
        AreaEstagio atualizada = areaService.updateArea(id, dados);
        return AreaEstagioMapper.toDTO(atualizada);
    }

    // DELETE
    // DELETE /api/areas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        areaService.deleteArea(id);
        return ResponseEntity.noContent().build();
    }
}