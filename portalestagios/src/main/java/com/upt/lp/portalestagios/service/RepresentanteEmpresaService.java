package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.repository.RepresentanteEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RepresentanteEmpresaService {

    @Autowired
    private RepresentanteEmpresaRepository representanteRepository;

    public List<RepresentanteEmpresa> findAll() {
        return representanteRepository.findAll();
    }

    public Optional<RepresentanteEmpresa> findById(UUID id) {
        return representanteRepository.findById(id);
    }

    public RepresentanteEmpresa save(RepresentanteEmpresa rep) {
        return representanteRepository.save(rep);
    }

    public void delete(UUID id) {
        representanteRepository.deleteById(id);
    }
}


