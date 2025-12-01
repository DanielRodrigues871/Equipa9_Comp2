package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.DepartamentoDTO;
import com.upt.lp.componente2.entity.Departamento;
import com.upt.lp.componente2.mapper.DepartamentoMapper;
import com.upt.lp.componente2.service.DepartamentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
    
    private final DepartamentoService departamentoService;
    
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }
    
    @GetMapping
    public List<DepartamentoDTO> getAllDepartamentos() {
        return departamentoService.getAllDepartamentos()
                .stream()
                .map(DepartamentoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public DepartamentoDTO getDepartamentoById(@PathVariable String id) {
        Departamento departamento = departamentoService.getDepartamentoById(id);
        return DepartamentoMapper.toDTO(departamento);
    }
    
    @PostMapping
    public DepartamentoDTO createDepartamento(@RequestBody Departamento departamento) {
        Departamento novoDepartamento = departamentoService.createDepartamento(departamento);
        return DepartamentoMapper.toDTO(novoDepartamento);
    }
    
    @PutMapping("/{id}")
    public DepartamentoDTO updateDepartamento(@PathVariable String id, @RequestBody Departamento departamento) {
        Departamento departamentoAtualizado = departamentoService.updateDepartamento(id, departamento);
        return DepartamentoMapper.toDTO(departamentoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteDepartamento(@PathVariable String id) {
        departamentoService.deleteDepartamento(id);
    }
    
    @GetMapping("/search")
    public List<DepartamentoDTO> searchDepartamentosByNome(@RequestParam String nome) {
        return departamentoService.searchDepartamentosByNome(nome)
                .stream()
                .map(DepartamentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
