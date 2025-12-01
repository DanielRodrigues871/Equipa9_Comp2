package com.upt.lp.portalestagios.controller;

import com.upt.lp.portalestagios.dto.representante.RepresentanteEmpresaRequestDTO;
import com.upt.lp.portalestagios.dto.representante.RepresentanteEmpresaResponseDTO;
import com.upt.lp.portalestagios.mapper.RepresentanteEmpresaMapper;
import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.service.RepresentanteEmpresaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/representantes")
public class RepresentanteEmpresaController {

    private final RepresentanteEmpresaService repService;

    public RepresentanteEmpresaController(RepresentanteEmpresaService repService) {
        this.repService = repService;
    }

    @GetMapping
    public List<RepresentanteEmpresaResponseDTO> listar() {
        return repService.findAll()
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public RepresentanteEmpresaResponseDTO buscarPorId(@PathVariable UUID id) {
        RepresentanteEmpresa r = repService.findById(id)
                .orElseThrow(() -> new RuntimeException("Representante não encontrado"));
        return RepresentanteEmpresaMapper.toDTO(r);
    }

    @PostMapping
    public RepresentanteEmpresaResponseDTO criar(@RequestBody RepresentanteEmpresaRequestDTO dto) {
        RepresentanteEmpresa r = repService.create(dto);
        return RepresentanteEmpresaMapper.toDTO(r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repService.delete(id);
    }
}
