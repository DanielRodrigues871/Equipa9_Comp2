package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.PropostaEstagio;
import com.upt.lp.portalestagios.repository.PropostaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropostaEstagioService {

    @Autowired
    private PropostaEstagioRepository propostaEstagioRepository;

    public List<PropostaEstagio> findAll() {
        return propostaEstagioRepository.findAll();
    }

    public Optional<PropostaEstagio> findById(String id) {
        return propostaEstagioRepository.findById(id);
    }

    public PropostaEstagio save(PropostaEstagio proposta) {
        return propostaEstagioRepository.save(proposta);
    }

    public void delete(String id) {
        propostaEstagioRepository.deleteById(id);
    }
}

