package com.upt.lp.portalestagios.service;


import com.upt.lp.portalestagios.entity.AreaEstagio;
import com.upt.lp.portalestagios.repository.AreaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaEstagioService {

    private final AreaEstagioRepository areaEstagioRepository;

    @Autowired
    public AreaEstagioService(AreaEstagioRepository areaEstagioRepository) {
        this.areaEstagioRepository = areaEstagioRepository;
    }

    // Retorna todas as áreas de estágio
    public List<AreaEstagio> listarTodas() {
        return areaEstagioRepository.findAll();
    }

    // Busca uma área pelo ID
    public Optional<AreaEstagio> buscarPorId(String id) {
        return areaEstagioRepository.findById(id);
    }

    // Cria uma nova área de estágio
    public AreaEstagio criar(AreaEstagio areaEstagio) {
        return areaEstagioRepository.save(areaEstagio);
    }

    // Atualiza uma área existente pelo ID
    public Optional<AreaEstagio> atualizar(String id, AreaEstagio areaAtualizada) {
        return areaEstagioRepository.findById(id).map(area -> {
            area.setNome(areaAtualizada.getNome());
            area.setDescricao(areaAtualizada.getDescricao());
            return areaEstagioRepository.save(area);
        });
    }

    // Remove uma área pelo ID
    public boolean deletar(String id) {
        return areaEstagioRepository.findById(id).map(area -> {
            areaEstagioRepository.delete(area);
            return true;
        }).orElse(false);
    }
}

