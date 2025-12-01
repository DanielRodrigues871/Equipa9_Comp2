package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.EstudanteDTO;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.mapper.EstudanteMapper;
import com.upt.lp.componente2.service.EstudanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {
    
    private final EstudanteService estudanteService;
    
    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }
    
    @GetMapping
    public List<EstudanteDTO> getAllEstudantes() {
        return estudanteService.getAllEstudantes()
                .stream()
                .map(EstudanteMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public EstudanteDTO getEstudanteById(@PathVariable String id) {
        Estudante estudante = estudanteService.getEstudanteById(id);
        return EstudanteMapper.toDTO(estudante);
    }
    
    @GetMapping("/email/{email}")
    public EstudanteDTO getEstudanteByEmail(@PathVariable String email) {
        Estudante estudante = estudanteService.getEstudanteByEmail(email);
        return EstudanteMapper.toDTO(estudante);
    }
    
    @GetMapping("/numero/{numeroEstudante}")
    public EstudanteDTO getEstudanteByNumero(@PathVariable String numeroEstudante) {
        Estudante estudante = estudanteService.getEstudanteByNumeroEstudante(numeroEstudante);
        return EstudanteMapper.toDTO(estudante);
    }
    
    @PostMapping
    public EstudanteDTO createEstudante(@RequestBody Estudante estudante,
                                       @RequestParam(required = false) String cursoId) {
        Estudante novoEstudante = estudanteService.createEstudante(estudante, cursoId);
        return EstudanteMapper.toDTO(novoEstudante);
    }
    
    @PutMapping("/{id}")
    public EstudanteDTO updateEstudante(@PathVariable String id, @RequestBody Estudante estudante) {
        Estudante estudanteAtualizado = estudanteService.updateEstudante(id, estudante);
        return EstudanteMapper.toDTO(estudanteAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteEstudante(@PathVariable String id) {
        estudanteService.deleteEstudante(id);
    }
    
    @GetMapping("/curso/{cursoId}")
    public List<EstudanteDTO> getEstudantesByCurso(@PathVariable String cursoId) {
        return estudanteService.getEstudantesByCurso(cursoId)
                .stream()
                .map(EstudanteMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ano/{anoMatricula}")
    public List<EstudanteDTO> getEstudantesByAnoMatricula(@PathVariable int anoMatricula) {
        return estudanteService.getEstudantesByAnoMatricula(anoMatricula)
                .stream()
                .map(EstudanteMapper::toDTO)
                .collect(Collectors.toList());
    }
}