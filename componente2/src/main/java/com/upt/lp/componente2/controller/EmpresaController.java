package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.EmpresaDTO;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.mapper.EmpresaMapper;
import com.upt.lp.componente2.service.EmpresaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    
    private final EmpresaService empresaService;
    
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
    
    @GetMapping
    public List<EmpresaDTO> getAllEmpresas() {
        return empresaService.getAllEmpresas()
                .stream()
                .map(EmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public EmpresaDTO getEmpresaById(@PathVariable String id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        return EmpresaMapper.toDTO(empresa);
    }
    
    @GetMapping("/nif/{nif}")
    public EmpresaDTO getEmpresaByNif(@PathVariable String nif) {
        Empresa empresa = empresaService.getEmpresaByNif(nif);
        return EmpresaMapper.toDTO(empresa);
    }
    
    @PostMapping
    public EmpresaDTO createEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaService.createEmpresa(empresa);
        return EmpresaMapper.toDTO(novaEmpresa);
    }
    
    @PutMapping("/{id}")
    public EmpresaDTO updateEmpresa(@PathVariable String id, @RequestBody Empresa empresa) {
        Empresa empresaAtualizada = empresaService.updateEmpresa(id, empresa);
        return EmpresaMapper.toDTO(empresaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public void deleteEmpresa(@PathVariable String id) {
        empresaService.deleteEmpresa(id);
    }
    
    @GetMapping("/search")
    public List<EmpresaDTO> searchEmpresasByNome(@RequestParam String nome) {
        return empresaService.searchEmpresasByNome(nome)
                .stream()
                .map(EmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ativas")
    public List<EmpresaDTO> getEmpresasAtivas() {
        return empresaService.getEmpresasAtivas()
                .stream()
                .map(EmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/inativas")
    public List<EmpresaDTO> getEmpresasInativas() {
        return empresaService.getEmpresasInativas()
                .stream()
                .map(EmpresaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @PutMapping("/{id}/ativar")
    public void ativarEmpresa(@PathVariable String id) {
        empresaService.ativarEmpresa(id);
    }
    
    @PutMapping("/{id}/desativar")
    public void desativarEmpresa(@PathVariable String id) {
        empresaService.desativarEmpresa(id);
    }
}