package com.upt.lp.componente2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.componente2.dto.EmpresaDTO;
import com.upt.lp.componente2.entity.Empresa;
import com.upt.lp.componente2.mapper.EmpresaMapper;
import com.upt.lp.componente2.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // CREATE
    // POST /api/empresas
    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@RequestBody EmpresaDTO dto) {
        Empresa entidade = EmpresaMapper.toEntity(dto);
        Empresa criada = empresaService.createEmpresa(entidade);
        EmpresaDTO resposta = EmpresaMapper.toDTO(criada);
        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    // READ todos
    // GET /api/empresas
    @GetMapping
    public List<EmpresaDTO> getAll() {
        return empresaService.getAllEmpresas()
                .stream()
                .map(EmpresaMapper::toDTO)
                .toList();
    }

    // READ por id
    // GET /api/empresas/{id}
    @GetMapping("/{id}")
    public EmpresaDTO getById(@PathVariable String id) {
        Empresa e = empresaService.getEmpresaById(id);
        return EmpresaMapper.toDTO(e);
    }

    // UPDATE
    // PUT /api/empresas/{id}
    @PutMapping("/{id}")
    public EmpresaDTO update(@PathVariable String id,
                             @RequestBody EmpresaDTO dto) {
        Empresa dados = EmpresaMapper.toEntity(dto);
        Empresa atualizada = empresaService.updateEmpresa(id, dados);
        return EmpresaMapper.toDTO(atualizada);
    }

    // ATIVAR / DESATIVAR
    // POST /api/empresas/{id}/ativar
    @PostMapping("/{id}/ativar")
    public EmpresaDTO ativar(@PathVariable String id) {
        Empresa e = empresaService.ativarEmpresa(id);
        return EmpresaMapper.toDTO(e);
    }

    // POST /api/empresas/{id}/desativar
    @PostMapping("/{id}/desativar")
    public EmpresaDTO desativar(@PathVariable String id) {
        Empresa e = empresaService.desativarEmpresa(id);
        return EmpresaMapper.toDTO(e);
    }

    // DELETE
    // DELETE /api/empresas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}