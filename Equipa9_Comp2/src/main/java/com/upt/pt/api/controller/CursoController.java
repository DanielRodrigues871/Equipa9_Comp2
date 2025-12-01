package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.CursoDTO;
import com.upt.pt.api.entity.Curso;
import com.upt.pt.api.mapper.CursoMapper;
import com.upt.pt.api.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // CREATE
    // POST /api/cursos?departamentoId=XXX&coordenadorId=YYY (coordenadorId opcional)
    @PostMapping
    public ResponseEntity<CursoDTO> create(@RequestBody CursoDTO dto, @RequestParam String departamentoId, @RequestParam(required = false) String coordenadorId) {

        Curso entidade = CursoMapper.toEntity(dto);
        Curso criado = cursoService.createCurso(entidade, departamentoId, coordenadorId);
        CursoDTO resposta = CursoMapper.toDTO(criado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/cursos
    @GetMapping
    public List<CursoDTO> getAll() {
        return cursoService.getAllCursos()
                .stream()
                .map(CursoMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/cursos/{id}
    @GetMapping("/{id}")
    public CursoDTO getById(@PathVariable String id) {
        Curso c = cursoService.getCursoById(id);
        return CursoMapper.toDTO(c);
    }

    // READ por departamento
    // GET /api/cursos/departamento/{departamentoId}
    @GetMapping("/departamento/{departamentoId}")
    public List<CursoDTO> getByDepartamento(@PathVariable String departamentoId) {
        return cursoService.getCursosByDepartamento(departamentoId)
                .stream()
                .map(CursoMapper::toDTO)
                .toList();
    }

    // READ por coordenador
    // GET /api/cursos/coordenador/{coordenadorId}
    @GetMapping("/coordenador/{coordenadorId}")
    public List<CursoDTO> getByCoordenador(@PathVariable String coordenadorId) {
        return cursoService.getCursosByCoordenador(coordenadorId)
                .stream()
                .map(CursoMapper::toDTO)
                .toList();
    }

    // UPDATE
    // PUT /api/cursos/{id}?departamentoId=XXX&coordenadorId=YYY
    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable String id, @RequestBody CursoDTO dto, @RequestParam String departamentoId, @RequestParam(required = false) String coordenadorId) {

        Curso dados = CursoMapper.toEntity(dto);
        Curso atualizado =
                cursoService.updateCurso(id, dados, departamentoId, coordenadorId);

        return CursoMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/cursos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
