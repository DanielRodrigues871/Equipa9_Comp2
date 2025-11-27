package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Estagio;
import com.upt.lp.portalestagios.repository.EstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstagioService {

    @Autowired
    private EstagioRepository estagioRepository;

    public List<Estagio> findAll() {
        return estagioRepository.findAll();
    }

    public Optional<Estagio> findById(String id) {
        return estagioRepository.findById(id);
    }

    public Estagio save(Estagio estagio) {
        return estagioRepository.save(estagio);
    }

    public void delete(String id) {
        estagioRepository.deleteById(id);
    }
}
