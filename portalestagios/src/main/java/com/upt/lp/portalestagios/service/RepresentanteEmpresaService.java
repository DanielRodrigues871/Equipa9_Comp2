package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.RepresentanteEmpresa;
import com.upt.lp.portalestagios.repository.RepresentanteEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteEmpresaService {

    @Autowired
    private RepresentanteEmpresaRepository representanteRepository;

    public List<RepresentanteEmpresa> findAll() {
        return representanteRepository.findAll();
    }

    public Optional<RepresentanteEmpresa> findById(String id) {
        return representanteRepository.findById(id);
    }

    public RepresentanteEmpresa save(RepresentanteEmpresa rep) {
        return representanteRepository.save(rep);
    }

    public void delete(String id) {
        representanteRepository.deleteById(id);
    }
}
