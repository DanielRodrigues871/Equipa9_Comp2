package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import com.upt.lp.portalestagios.repository.OfertaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaEstagioService {

    @Autowired
    private OfertaEstagioRepository ofertaRepository;

    public List<OfertaEstagio> findAll() {
        return ofertaRepository.findAll();
    }

    public Optional<OfertaEstagio> findById(String id) {
        return ofertaRepository.findById(id);
    }

    public OfertaEstagio save(OfertaEstagio oferta) {
        return ofertaRepository.save(oferta);
    }

    public void delete(String id) {
        ofertaRepository.deleteById(id);
    }

    /** Métodos específicos */
    public OfertaEstagio aprovar(String id) {
        OfertaEstagio oferta = ofertaRepository.findById(id).orElseThrow();
        oferta.aprovar();
        return ofertaRepository.save(oferta);
    }

    public OfertaEstagio rejeitar(String id) {
        OfertaEstagio oferta = ofertaRepository.findById(id).orElseThrow();
        oferta.rejeitar();
        return ofertaRepository.save(oferta);
    }
}
