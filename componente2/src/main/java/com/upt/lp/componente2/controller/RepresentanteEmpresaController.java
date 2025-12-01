package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.RepresentanteEmpresaDTO;
import com.upt.lp.componente2.entity.RepresentanteEmpresa;
import com.upt.lp.componente2.mapper.RepresentanteEmpresaMapper;
import com.upt.lp.componente2.service.RepresentanteEmpresaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/representantes")
public class RepresentanteEmpresaController {
    
    private final RepresentanteEmpresaService representanteService;
    
    public RepresentanteEmpresaController(RepresentanteEmpresaService representanteService) {
        this.representanteService = representanteService;
    }
    
    @GetMapping
    public List<RepresentanteEmpresaDTO> getAllRepresentantes() {
        return representanteService.getAllRepresentantes()
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public RepresentanteEmpresaDTO getRepresentanteById(@PathVariable String id) {
        RepresentanteEmpresa representante = representanteService.getRepresentanteById(id);
        return RepresentanteEmpresaMapper.toDTO(representante);
    }
    
    @GetMapping("/email/{email}")
    public RepresentanteEmpresaDTO getRepresentanteByEmail(@PathVariable String email) {
        RepresentanteEmpresa representante = representanteService.getRepresentanteByEmail(email);
        return RepresentanteEmpresaMapper.toDTO(representante);
    }
    
    @PostMapping
    public RepresentanteEmpresaDTO createRepresentante(@RequestBody RepresentanteEmpresa representante,
                                                      @RequestParam(required = false) String empresaId) {
        RepresentanteEmpresa novoRepresentante = representanteService.createRepresentante(representante, empresaId);
        return RepresentanteEmpresaMapper.toDTO(novoRepresentante);
    }
    
    @PutMapping("/{id}")
    public RepresentanteEmpresaDTO updateRepresentante(@PathVariable String id, @RequestBody RepresentanteEmpresa representante) {
        RepresentanteEmpresa representanteAtualizado = representanteService.updateRepresentante(id, representante);
        return RepresentanteEmpresaMapper.toDTO(representanteAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteRepresentante(@PathVariable String id) {
        representanteService.deleteRepresentante(id);
    }
    
    @GetMapping("/empresa/{empresaId}")
    public List<RepresentanteEmpresaDTO> getRepresentantesByEmpresa(@PathVariable String empresaId) {
        return representanteService.getRepresentantesByEmpresa(empresaId)
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/cargo/{cargo}")
    public List<RepresentanteEmpresaDTO> getRepresentantesByCargo(@PathVariable String cargo) {
        return representanteService.getRepresentantesByCargo(cargo)
                .stream()
                .map(RepresentanteEmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
