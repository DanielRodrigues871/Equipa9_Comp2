package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.CandidaturaDTO;
import com.upt.lp.componente2.entity.Candidatura;
import com.upt.lp.componente2.enums.StatusCandidatura;
import com.upt.lp.componente2.mapper.CandidaturaMapper;
import com.upt.lp.componente2.service.CandidaturaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {
    
    private final CandidaturaService candidaturaService;
    
    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }
    
    @GetMapping
    public List<CandidaturaDTO> getAllCandidaturas() {
        return candidaturaService.getAllCandidaturas()
                .stream()
                .map(CandidaturaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public CandidaturaDTO getCandidaturaById(@PathVariable String id) {
        Candidatura candidatura = candidaturaService.getCandidaturaById(id);
        return CandidaturaMapper.toDTO(candidatura);
    }
    
    @PostMapping
    public CandidaturaDTO createCandidatura(@RequestParam String estudanteId,
                                           @RequestParam String ofertaId,
                                           @RequestParam String cartaMotivacao) {
        Candidatura novaCandidatura = candidaturaService.createCandidatura(estudanteId, ofertaId, cartaMotivacao);
        return CandidaturaMapper.toDTO(novaCandidatura);
    }
    
    @PutMapping("/{id}/analise")
    public void colocarEmAnalise(@PathVariable String id, @RequestParam String coordenadorId) {
        candidaturaService.colocarEmAnalise(id, coordenadorId);
    }
    
    @PutMapping("/{id}/aprovar")
    public void aprovarCandidatura(@PathVariable String id) {
        candidaturaService.aprovarCandidatura(id);
    }
    
    @PutMapping("/{id}/rejeitar")
    public void rejeitarCandidatura(@PathVariable String id, @RequestParam String observacoes) {
        candidaturaService.rejeitarCandidatura(id, observacoes);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCandidatura(@PathVariable String id) {
        candidaturaService.deleteCandidatura(id);
    }
    
    @GetMapping("/estudante/{estudanteId}")
    public List<CandidaturaDTO> getCandidaturasByEstudante(@PathVariable String estudanteId) {
        return candidaturaService.getCandidaturasByEstudante(estudanteId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/oferta/{ofertaId}")
    public List<CandidaturaDTO> getCandidaturasByOferta(@PathVariable String ofertaId) {
        return candidaturaService.getCandidaturasByOferta(ofertaId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/coordenador/{coordenadorId}")
    public List<CandidaturaDTO> getCandidaturasByCoordenador(@PathVariable String coordenadorId) {
        return candidaturaService.getCandidaturasByCoordenador(coordenadorId)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/status/{status}")
    public List<CandidaturaDTO> getCandidaturasByStatus(@PathVariable StatusCandidatura status) {
        return candidaturaService.getCandidaturasByStatus(status)
                .stream()
                .map(CandidaturaMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/oferta/{ofertaId}/count")
    public long countCandidaturasByOferta(@PathVariable String ofertaId) {
        return candidaturaService.countCandidaturasByOferta(ofertaId);
    }
    
    @GetMapping("/estudante/{estudanteId}/count")
    public long countCandidaturasByEstudante(@PathVariable String estudanteId) {
        return candidaturaService.countCandidaturasByEstudante(estudanteId);
    }
}
