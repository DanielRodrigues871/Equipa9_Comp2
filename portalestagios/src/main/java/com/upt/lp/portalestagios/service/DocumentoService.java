package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Documento;
import com.upt.lp.portalestagios.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> findAll() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> findById(String id) {
        return documentoRepository.findById(id);
    }

    public Documento save(Documento documento) {
        return documentoRepository.save(documento);
    }

    public void delete(String id) {
        documentoRepository.deleteById(id);
    }
}
