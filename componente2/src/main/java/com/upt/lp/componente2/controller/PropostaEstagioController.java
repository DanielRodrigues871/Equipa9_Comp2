package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.PropostaEstagioDTO;
import com.upt.lp.componente2.entity.PropostaEstagio;
import com.upt.lp.componente2.mapper.PropostaEstagioMapper;
import com.upt.lp.componente2.service.PropostaEstagioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/propostas-estagio")
public class PropostaEstagioController {
    
    private final PropostaEstagioService propostaService;
    
    public PropostaEstagioController(PropostaEstagioService propostaService) {
        this.propostaService = propostaService;
    }
    
    @GetMapping
    public List<PropostaEstagioDTO> getAllPropostas() {
        return propostaService.getAllPropostas()
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public PropostaEstagioDTO getPropostaById(@PathVariable String id) {
        PropostaEstagio proposta = propostaService.getPropostaById(id);
        return PropostaEstagioMapper.toDTO(proposta);
    }
    
    @PostMapping
    public PropostaEstagioDTO createProposta(@RequestBody PropostaEstagio proposta,
                                            @RequestParam String empresaId,
                                            @RequestParam String representanteId,
                                            @RequestParam(required = false) List<String> areasIds) {
        PropostaEstagio novaProposta = propostaService.createProposta(proposta, empresaId, representanteId, areasIds);
        return PropostaEstagioMapper.toDTO(novaProposta);
    }
    
    @PutMapping("/{id}")
    public PropostaEstagioDTO updateProposta(@PathVariable String id, @RequestBody PropostaEstagio proposta) {
        PropostaEstagio propostaAtualizada = propostaService.updateProposta(id, proposta);
        return PropostaEstagioMapper.toDTO(propostaAtualizada);
    }
    
    @PutMapping("/{id}/aprovar")
    public void aprovarProposta(@PathVariable String id) {
        propostaService.aprovarProposta(id);
    }
    
    @PutMapping("/{id}/rejeitar")
    public void rejeitarProposta(@PathVariable String id) {
        propostaService.rejeitarProposta(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProposta(@PathVariable String id) {
        propostaService.deleteProposta(id);
    }
    
    @GetMapping("/empresa/{empresaId}")
    public List<PropostaEstagioDTO> getPropostasByEmpresa(@PathVariable String empresaId) {
        return propostaService.getPropostasByEmpresa(empresaId)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/representante/{representanteId}")
    public List<PropostaEstagioDTO> getPropostasByRepresentante(@PathVariable String representanteId) {
        return propostaService.getPropostasByRepresentante(representanteId)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/status/{status}")
    public List<PropostaEstagioDTO> getPropostasByStatus(@PathVariable String status) {
        return propostaService.getPropostasByStatus(status)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<PropostaEstagioDTO> getPropostasByTipo(@PathVariable String tipo) {
        return propostaService.getPropostasByTipo(tipo)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    public List<PropostaEstagioDTO> searchPropostasByTitulo(@RequestParam String titulo) {
        return propostaService.searchPropostasByTitulo(titulo)
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pendentes")
    public List<PropostaEstagioDTO> getPropostasPendentes() {
        return propostaService.getPropostasPendentes()
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/aprovadas")
    public List<PropostaEstagioDTO> getPropostasAprovadas() {
        return propostaService.getPropostasAprovadas()
                .stream()
                .map(PropostaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/status/{status}/count")
    public long countPropostasByStatus(@PathVariable String status) {
        return propostaService.countPropostasByStatus(status);
    }
}