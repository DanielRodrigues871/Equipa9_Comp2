package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.AreaEstagio;
import com.upt.lp.componente2.repository.AreaEstagioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AreaEstagioService {
    
    private final AreaEstagioRepository areaRepository;
    
    public AreaEstagioService(AreaEstagioRepository areaRepository) {
        this.areaRepository = areaRepository;
    }
    
    public List<AreaEstagio> getAllAreas() {
        return areaRepository.findAll();
    }
    
    public AreaEstagio getAreaById(String id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada com ID: " + id));
    }
    
    public AreaEstagio createArea(AreaEstagio area) {
        // Verificar se nome já existe
        if (areaRepository.findByNome(area.getNome()).isPresent()) {
            throw new RuntimeException("Já existe uma área com o nome: " + area.getNome());
        }
        
        return areaRepository.save(area);
    }
    
    public AreaEstagio updateArea(String id, AreaEstagio areaAtualizada) {
        AreaEstagio areaExistente = getAreaById(id);
        
        // Verificar se o novo nome já existe (se foi alterado)
        if (!areaExistente.getNome().equals(areaAtualizada.getNome()) && 
            areaRepository.findByNome(areaAtualizada.getNome()).isPresent()) {
            throw new RuntimeException("Já existe uma área com o nome: " + areaAtualizada.getNome());
        }
        
        areaExistente.setNome(areaAtualizada.getNome());
        areaExistente.setDescricao(areaAtualizada.getDescricao());
        
        return areaRepository.save(areaExistente);
    }
    
    public void deleteArea(String id) {
        if (!areaRepository.existsById(id)) {
            throw new RuntimeException("Área não encontrada com ID: " + id);
        }
        areaRepository.deleteById(id);
    }
    
    public List<AreaEstagio> searchAreasByNome(String nome) {
        return areaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
