package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.CoordenadorDTO;
import com.upt.lp.componente2.entity.Coordenador;
import com.upt.lp.componente2.mapper.CoordenadorMapper;
import com.upt.lp.componente2.service.CoordenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {
    
    private final CoordenadorService coordenadorService;
    
    public CoordenadorController(CoordenadorService coordenadorService) {
        this.coordenadorService = coordenadorService;
    }
    
    @GetMapping
    public List<CoordenadorDTO> getAllCoordenadores() {
        return coordenadorService.getAllCoordenadores()
                .stream()
                .map(CoordenadorMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public CoordenadorDTO getCoordenadorById(@PathVariable String id) {
        Coordenador coordenador = coordenadorService.getCoordenadorById(id);
        return CoordenadorMapper.toDTO(coordenador);
    }
    
    @GetMapping("/email/{email}")
    public CoordenadorDTO getCoordenadorByEmail(@PathVariable String email) {
        Coordenador coordenador = coordenadorService.getCoordenadorByEmail(email);
        return CoordenadorMapper.toDTO(coordenador);
    }
    
    @PostMapping
    public CoordenadorDTO createCoordenador(@RequestBody Coordenador coordenador, 
                                           @RequestParam String departamentoId) {
        Coordenador novoCoordenador = coordenadorService.createCoordenador(coordenador, departamentoId);
        return CoordenadorMapper.toDTO(novoCoordenador);
    }
    
    @PutMapping("/{id}")
    public CoordenadorDTO updateCoordenador(@PathVariable String id, @RequestBody Coordenador coordenador) {
        Coordenador coordenadorAtualizado = coordenadorService.updateCoordenador(id, coordenador);
        return CoordenadorMapper.toDTO(coordenadorAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCoordenador(@PathVariable String id) {
        coordenadorService.deleteCoordenador(id);
    }
    
    @GetMapping("/departamento/{departamentoId}")
    public List<CoordenadorDTO> getCoordenadoresByDepartamento(@PathVariable String departamentoId) {
        return coordenadorService.getCoordenadoresByDepartamento(departamentoId)
                .stream()
                .map(CoordenadorMapper::toDTO)
                .collect(Collectors.toList());
    }
}