package com.upt.lp.componente2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.componente2.dto.RepresentanteEmpresaDTO;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.mapper.RepresentanteEmpresaMapper;
import com.upt.lp.componente2.service.RepresentanteEmpresaService;

@RestController
@RequestMapping("/api/representantes")
public class RepresentanteEmpresaController {

    private final RepresentanteEmpresaService representanteService;

    public RepresentanteEmpresaController(RepresentanteEmpresaService representanteService) {
        this.representanteService = representanteService;
    }

    // CREATE
    // POST /api/representantes?empresaId=XYZ
    @PostMapping
    public ResponseEntity<RepresentanteEmpresaDTO> create(@RequestBody RepresentanteEmpresaDTO dto, @RequestParam String empresaId) {

        RepresentanteEmpresa entidade = RepresentanteEmpresaMapper.toEntity(dto);
        RepresentanteEmpresa criado =
                representanteService.createRepresentante(entidade, empresaId);

        RepresentanteEmpresaDTO resposta = RepresentanteEmpresaMapper.toDTO(criado);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/representantes
    @GetMapping
    public List<RepresentanteEmpresaDTO> getAll() {
        return representanteService.getAllRepresentantes()
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/representantes/{id}
    @GetMapping("/{id}")
    public RepresentanteEmpresaDTO getById(@PathVariable String id) {
        RepresentanteEmpresa r = representanteService.getRepresentanteById(id);
        return RepresentanteEmpresaMapper.toDTO(r);
    }

    // READ por empresa
    // GET /api/representantes/empresa/{empresaId}
    @GetMapping("/empresa/{empresaId}")
    public List<RepresentanteEmpresaDTO> getByEmpresa(@PathVariable String empresaId) {
        return representanteService.getRepresentantesByEmpresa(empresaId)
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .toList();
    }

    // UPDATE
    // PUT /api/representantes/{id}?empresaId=XYZ
    @PutMapping("/{id}")
    public RepresentanteEmpresaDTO update(@PathVariable String id, @RequestBody RepresentanteEmpresaDTO dto, @RequestParam String empresaId) {

        RepresentanteEmpresa dados = RepresentanteEmpresaMapper.toEntity(dto);
        RepresentanteEmpresa atualizado =
                representanteService.updateRepresentante(id, dados, empresaId);

        return RepresentanteEmpresaMapper.toDTO(atualizado);
    }

    // DELETE
    // DELETE /api/representantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        representanteService.deleteRepresentante(id);
        return ResponseEntity.noContent().build();
    }
}