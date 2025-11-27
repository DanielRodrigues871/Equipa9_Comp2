package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    public List<Coordenador> findAll() {
        return coordenadorRepository.findAll();
    }

    public Optional<Coordenador> findById(String id) {
        return coordenadorRepository.findById(id);
    }

    public Coordenador save(Coordenador coordenador) {
        return coordenadorRepository.save(coordenador);
    }

    public void delete(String id) {
        coordenadorRepository.deleteById(id);
    }
}
