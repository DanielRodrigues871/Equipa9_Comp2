package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.AreaEstagioDTO;
import com.upt.lp.componente2.entity.AreaEstagio;
import com.upt.lp.componente2.mapper.AreaEstagioMapper;
import com.upt.lp.componente2.service.AreaEstagioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/areas-estagio")
public class AreaEstagioController {
    
    private final AreaEstagioService areaService;
    
    public AreaEstagioController(AreaEstagioService areaService) {
        this.areaService = areaService;
    }
    
    @GetMapping
    public List<AreaEstagioDTO> getAllAreas() {
        return areaService.getAllAreas()
                .stream()
                .map(AreaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public AreaEstagioDTO getAreaById(@PathVariable String id) {
        AreaEstagio area = areaService.getAreaById(id);
        return AreaEstagioMapper.toDTO(area);
    }
    
    @PostMapping
    public AreaEstagioDTO createArea(@RequestBody AreaEstagio area) {
        AreaEstagio novaArea = areaService.createArea(area);
        return AreaEstagioMapper.toDTO(novaArea);
    }
    
    @PutMapping("/{id}")
    public AreaEstagioDTO updateArea(@PathVariable String id, @RequestBody AreaEstagio area) {
        AreaEstagio areaAtualizada = areaService.updateArea(id, area);
        return AreaEstagioMapper.toDTO(areaAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public void deleteArea(@PathVariable String id) {
        areaService.deleteArea(id);
    }
    
    @GetMapping("/search")
    public List<AreaEstagioDTO> searchAreasByNome(@RequestParam String nome) {
        return areaService.searchAreasByNome(nome)
                .stream()
                .map(AreaEstagioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
