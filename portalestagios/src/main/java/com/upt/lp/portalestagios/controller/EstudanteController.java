package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.estudante.EstudanteRequestDTO;
import com.upt.lp.portalestagios.dto.estudante.EstudanteResponseDTO;
import com.upt.lp.portalestagios.mapper.EstudanteMapper;
import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.service.EstudanteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @GetMapping
    public List<EstudanteResponseDTO> listarTodos() {
        return estudanteService.findAll()
                .stream()
                .map(EstudanteMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public EstudanteResponseDTO buscarPorId(@PathVariable UUID id) {
        Estudante e = estudanteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado!"));
        return EstudanteMapper.toDTO(e);
    }

    @PostMapping
    public EstudanteResponseDTO criar(@RequestBody EstudanteRequestDTO dto) {
        Estudante novo = estudanteService.create(dto);
        return EstudanteMapper.toDTO(novo);
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable UUID id) {
        estudanteService.delete(id);
    }
}
