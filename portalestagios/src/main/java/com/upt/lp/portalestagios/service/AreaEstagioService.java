package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.AreaEstagio;
import com.upt.lp.portalestagios.repository.AreaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaEstagioService {

    @Autowired
    private AreaEstagioRepository areaRepo;

    public List<AreaEstagio> findAll() {
        return areaRepo.findAll();
    }

    public Optional<AreaEstagio> findById(String id) {
        return areaRepo.findById(id);
    }

    public AreaEstagio save(AreaEstagio area) {
        return areaRepo.save(area);
    }

    public void delete(String id) {
        areaRepo.deleteById(id);
    }
}
