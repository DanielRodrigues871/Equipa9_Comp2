package com.upt.pt.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.pt.api.dto.CoordenadorDTO;
import com.upt.pt.api.entity.Coordenador;
import com.upt.pt.api.mapper.CoordenadorMapper;
import com.upt.pt.api.service.CoordenadorService;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {

    private final CoordenadorService coordenadorService;

    public CoordenadorController(CoordenadorService coordenadorService) {
        this.coordenadorService = coordenadorService;
    }

    // CREATE
    // POST /api/coordenadores?departamentoId=XYZ
    @PostMapping
    public ResponseEntity<CoordenadorDTO> create(@RequestBody CoordenadorDTO dto, @RequestParam String departamentoId) {

        Coordenador entidade = CoordenadorMapper.toEntity(dto);
        Coordenador criado = coordenadorService.createCoordenador(entidade, departamentoId);
        CoordenadorDTO resposta = CoordenadorMapper.toDTO(criado);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/coordenadores
    @GetMapping
    public List<CoordenadorDTO> getAll() {
        return coordenadorService.getAllCoordenadores()
                .stream()
                .map(CoordenadorMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/coordenadores/{id}
    @GetMapping("/{id}")
    public CoordenadorDTO getById(@PathVariable String id) {
        Coordenador c = coordenadorService.getCoordenadorById(id);
        return CoordenadorMapper.toDTO(c);
    }

    // UPDATE
    // PUT /api/coordenadores/{id}?departamentoId=XYZ
    @PutMapping("/{id}")
    public CoordenadorDTO update(@PathVariable String id, @RequestBody CoordenadorDTO dto, @RequestParam String departamentoId) {

        Coordenador dados = CoordenadorMapper.toEntity(dto);
        Coordenador atualizado =
                coordenadorService.updateCoordenador(id, dados, departamentoId);

        return CoordenadorMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/coordenadores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        coordenadorService.deleteCoordenador(id);
        return ResponseEntity.noContent().build();
    }
}
