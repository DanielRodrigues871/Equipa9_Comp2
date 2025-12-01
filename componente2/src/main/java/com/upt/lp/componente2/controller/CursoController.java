package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.CursoDTO;
import com.upt.lp.componente2.entity.Curso;
import com.upt.lp.componente2.mapper.CursoMapper;
import com.upt.lp.componente2.service.CursoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    
    private final CursoService cursoService;
    
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    
    @GetMapping
    public List<CursoDTO> getAllCursos() {
        return cursoService.getAllCursos()
                .stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public CursoDTO getCursoById(@PathVariable String id) {
        Curso curso = cursoService.getCursoById(id);
        return CursoMapper.toDTO(curso);
    }
    
    @PostMapping
    public CursoDTO createCurso(@RequestBody Curso curso,
                               @RequestParam(required = false) String departamentoId,
                               @RequestParam(required = false) String coordenadorId) {
        Curso novoCurso = cursoService.createCurso(curso, departamentoId, coordenadorId);
        return CursoMapper.toDTO(novoCurso);
    }
    
    @PutMapping("/{id}")
    public CursoDTO updateCurso(@PathVariable String id, @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.updateCurso(id, curso);
        return CursoMapper.toDTO(cursoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable String id) {
        cursoService.deleteCurso(id);
    }
    
    @GetMapping("/departamento/{departamentoId}")
    public List<CursoDTO> getCursosByDepartamento(@PathVariable String departamentoId) {
        return cursoService.getCursosByDepartamento(departamentoId)
                .stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/coordenador/{coordenadorId}")
    public List<CursoDTO> getCursosByCoordenador(@PathVariable String coordenadorId) {
        return cursoService.getCursosByCoordenador(coordenadorId)
                .stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    public List<CursoDTO> searchCursosByNome(@RequestParam String nome) {
        return cursoService.searchCursosByNome(nome)
                .stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
