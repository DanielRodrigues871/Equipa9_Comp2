package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.DepartamentoDTO;
import com.upt.pt.api.entity.Departamento;
import com.upt.pt.api.mapper.DepartamentoMapper;
import com.upt.pt.api.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    // CREATE
    // POST /api/departamentos
    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(@RequestBody DepartamentoDTO dto) {
        Departamento entidade = DepartamentoMapper.toEntity(dto);
        Departamento criado = departamentoService.createDepartamento(entidade);
        DepartamentoDTO resposta = DepartamentoMapper.toDTO(criado);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/departamentos
    @GetMapping
    public List<DepartamentoDTO> getAll() {
        return departamentoService.getAllDepartamentos()
                .stream()
                .map(DepartamentoMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/departamentos/{id}
    @GetMapping("/{id}")
    public DepartamentoDTO getById(@PathVariable String id) {
        Departamento d = departamentoService.getDepartamentoById(id);
        return DepartamentoMapper.toDTO(d);
    }

    // UPDATE
    // PUT /api/departamentos/{id}
    @PutMapping("/{id}")
    public DepartamentoDTO update(@PathVariable String id, @RequestBody DepartamentoDTO dto) {

        Departamento dados = DepartamentoMapper.toEntity(dto);
        Departamento atualizado = departamentoService.updateDepartamento(id, dados);
        return DepartamentoMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/departamentos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        departamentoService.deleteDepartamento(id);
        return ResponseEntity.noContent().build();
    }
}
