package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Estudante;
import com.upt.lp.portalestagios.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public List<Estudante> findAll() {
        return estudanteRepository.findAll();
    }

    public Optional<Estudante> findById(String id) {
        return estudanteRepository.findById(id);
    }

    public Estudante save(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    public void delete(String id) {
        estudanteRepository.deleteById(id);
    }
}

