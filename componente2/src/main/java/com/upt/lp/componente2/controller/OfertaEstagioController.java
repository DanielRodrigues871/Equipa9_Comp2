package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.OfertaEstagioDTO;
import com.upt.lp.componente2.entity.OfertaEstagio;
import com.upt.lp.componente2.enums.StatusOferta;
import com.upt.lp.componente2.enums.TipoEstagio;
import com.upt.lp.componente2.mapper.OfertaEstagioMapper;
import com.upt.lp.componente2.service.OfertaEstagioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ofertas-estagio")
public class OfertaEstagioController {
    
    private final OfertaEstagioService ofertaService;
    
    public OfertaEstagioController(OfertaEstagioService ofertaService) {
        this.ofertaService = ofertaService;
    }
    
    @GetMapping
    public List<OfertaEstagioDTO> getAllOfertas() {
        return ofertaService.getAllOfertas()
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public OfertaEstagioDTO getOfertaById(@PathVariable String id) {
        OfertaEstagio oferta = ofertaService.getOfertaById(id);
        return OfertaEstagioMapper.toDTO(oferta);
    }
    
    @PostMapping
    public OfertaEstagioDTO createOferta(@RequestBody OfertaEstagio oferta,
                                        @RequestParam String empresaId,
                                        @RequestParam(required = false) String areaId,
                                        @RequestParam(required = false) String cursoId,
                                        @RequestParam(required = false) String coordenadorId) {
        OfertaEstagio novaOferta = ofertaService.createOferta(oferta, empresaId, areaId, cursoId, coordenadorId);
        return OfertaEstagioMapper.toDTO(novaOferta);
    }
    
    @PutMapping("/{id}")
    public OfertaEstagioDTO updateOferta(@PathVariable String id, @RequestBody OfertaEstagio oferta) {
        OfertaEstagio ofertaAtualizada = ofertaService.updateOferta(id, oferta);
        return OfertaEstagioMapper.toDTO(ofertaAtualizada);
    }
    
    @PutMapping("/{id}/aprovar")
    public void aprovarOferta(@PathVariable String id) {
        ofertaService.aprovarOferta(id);
    }
    
    @PutMapping("/{id}/rejeitar")
    public void rejeitarOferta(@PathVariable String id) {
        ofertaService.rejeitarOferta(id);
    }
    
    @PutMapping("/{id}/encerrar")
    public void encerrarOferta(@PathVariable String id) {
        ofertaService.encerrarOferta(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOferta(@PathVariable String id) {
        ofertaService.deleteOferta(id);
    }
    
    @GetMapping("/empresa/{empresaId}")
    public List<OfertaEstagioDTO> getOfertasByEmpresa(@PathVariable String empresaId) {
        return ofertaService.getOfertasByEmpresa(empresaId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/curso/{cursoId}")
    public List<OfertaEstagioDTO> getOfertasByCurso(@PathVariable String cursoId) {
        return ofertaService.getOfertasByCurso(cursoId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/area/{areaId}")
    public List<OfertaEstagioDTO> getOfertasByArea(@PathVariable String areaId) {
        return ofertaService.getOfertasByArea(areaId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/status/{status}")
    public List<OfertaEstagioDTO> getOfertasByStatus(@PathVariable StatusOferta status) {
        return ofertaService.getOfertasByStatus(status)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<OfertaEstagioDTO> getOfertasByTipo(@PathVariable TipoEstagio tipo) {
        return ofertaService.getOfertasByTipo(tipo)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/coordenador/{coordenadorId}")
    public List<OfertaEstagioDTO> getOfertasByCoordenador(@PathVariable String coordenadorId) {
        return ofertaService.getOfertasByCoordenador(coordenadorId)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    public List<OfertaEstagioDTO> searchOfertasByTitulo(@RequestParam String titulo) {
        return ofertaService.searchOfertasByTitulo(titulo)
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/ativas")
    public List<OfertaEstagioDTO> getOfertasAtivas() {
        return ofertaService.getOfertasAtivas()
                .stream()
                .map(OfertaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/status/{status}/count")
    public long countOfertasByStatus(@PathVariable StatusOferta status) {
        return ofertaService.countOfertasByStatus(status);
    }
}
