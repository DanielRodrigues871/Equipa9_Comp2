package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.EstudanteDTO;
import com.upt.pt.api.entity.Estudante;
import com.upt.pt.api.mapper.EstudanteMapper;
import com.upt.pt.api.service.EstudanteService;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    // CREATE
    // POST /api/estudantes?cursoId=XYZ
    @PostMapping
    public ResponseEntity<EstudanteDTO> create(@RequestBody EstudanteDTO dto, @RequestParam String cursoId) {

        Estudante entidade = EstudanteMapper.toEntity(dto);
        Estudante criado = estudanteService.createEstudante(entidade, cursoId);
        EstudanteDTO resposta = EstudanteMapper.toDTO(criado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/estudantes
    @GetMapping
    public List<EstudanteDTO> getAll() {
        return estudanteService.getAllEstudantes()
                .stream()
                .map(EstudanteMapper::toDTO)
                .toList();
    }
    
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<EstudanteDTO>> getByCurso(@PathVariable String cursoId) {
        
        List<EstudanteDTO> lista = estudanteService.getEstudantesByCurso(cursoId)
                .stream()
                .map(EstudanteMapper::toDTO)
                .toList();
                
        return ResponseEntity.ok(lista);
    }

    // READ por id
    // GET /api/estudantes/{id}
    @GetMapping("/{id}")
    public EstudanteDTO getById(@PathVariable String id) {
        Estudante e = estudanteService.getEstudanteById(id);
        return EstudanteMapper.toDTO(e);
    }

    // UPDATE
    // PUT /api/estudantes/{id}?cursoId=XYZ
    @PutMapping("/{id}")
    public EstudanteDTO update(@PathVariable String id, @RequestBody EstudanteDTO dto, @RequestParam String cursoId) {

        Estudante dados = EstudanteMapper.toEntity(dto);
        Estudante atualizado = estudanteService.updateEstudante(id, dados, cursoId);
        return EstudanteMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/estudantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        estudanteService.deleteEstudante(id);
        return ResponseEntity.noContent().build();
    }

    // EXTRA: estudantes com média >= 9.5
    // GET /api/estudantes/honras
    @GetMapping("/honras")
    public List<EstudanteDTO> getComMediaMaiorOuIgualA9_5() {
        return estudanteService.getEstudantesComMediaMaiorQue9_5()
                .stream()
                .map(EstudanteMapper::toDTO)
                .toList();
    }
}
