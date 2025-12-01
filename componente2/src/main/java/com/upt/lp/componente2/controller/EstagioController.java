package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.EstagioDTO;
import com.upt.lp.componente2.entity.Estagio;
import com.upt.lp.componente2.mapper.EstagioMapper;
import com.upt.lp.componente2.service.EstagioService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estagios")
public class EstagioController {
    
    private final EstagioService estagioService;
    
    public EstagioController(EstagioService estagioService) {
        this.estagioService = estagioService;
    }
    
    @GetMapping
    public List<EstagioDTO> getAllEstagios() {
        return estagioService.getAllEstagios()
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public EstagioDTO getEstagioById(@PathVariable String id) {
        Estagio estagio = estagioService.getEstagioById(id);
        return EstagioMapper.toDTO(estagio);
    }
    
    @PostMapping
    public EstagioDTO createEstagio(@RequestBody Estagio estagio,
                                   @RequestParam String estudanteId,
                                   @RequestParam String ofertaId) {
        Estagio novoEstagio = estagioService.createEstagio(estagio, estudanteId, ofertaId);
        return EstagioMapper.toDTO(novoEstagio);
    }
    
    @PutMapping("/{id}")
    public EstagioDTO updateEstagio(@PathVariable String id, @RequestBody Estagio estagio) {
        Estagio estagioAtualizado = estagioService.updateEstagio(id, estagio);
        return EstagioMapper.toDTO(estagioAtualizado);
    }
    
    @PutMapping("/{id}/concluir")
    public void concluirEstagio(@PathVariable String id,
                               @RequestParam String notaFinal,
                               @RequestParam LocalDate dataFim) {
        estagioService.concluirEstagio(id, notaFinal, dataFim);
    }
    
    @PutMapping("/{id}/cancelar")
    public void cancelarEstagio(@PathVariable String id, @RequestParam String observacoes) {
        estagioService.cancelarEstagio(id, observacoes);
    }
    
    @DeleteMapping("/{id}")
    public void deleteEstagio(@PathVariable String id) {
        estagioService.deleteEstagio(id);
    }
    
    @GetMapping("/estudante/{estudanteId}")
    public List<EstagioDTO> getEstagiosByEstudante(@PathVariable String estudanteId) {
        return estagioService.getEstagiosByEstudante(estudanteId)
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/empresa/{empresaId}")
    public List<EstagioDTO> getEstagiosByEmpresa(@PathVariable String empresaId) {
        return estagioService.getEstagiosByEmpresa(empresaId)
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/curso/{cursoId}")
    public List<EstagioDTO> getEstagiosByCurso(@PathVariable String cursoId) {
        return estagioService.getEstagiosByCurso(cursoId)
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/estado/{estado}")
    public List<EstagioDTO> getEstagiosByEstado(@PathVariable String estado) {
        return estagioService.getEstagiosByEstado(estado)
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/concluidos")
    public List<EstagioDTO> getEstagiosConcluidos() {
        return estagioService.getEstagiosConcluidos()
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/em-curso")
    public List<EstagioDTO> getEstagiosEmCurso() {
        return estagioService.getEstagiosEmCurso()
                .stream()
                .map(EstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
