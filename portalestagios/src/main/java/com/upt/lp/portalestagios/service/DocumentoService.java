package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Documento;
import com.upt.lp.portalestagios.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> findAll() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> findById(UUID id) {
        return documentoRepository.findById(id);
    }

    public List<Documento> findByEstagio(UUID estagioId) {
        return documentoRepository.findByEstagio_Id(estagioId);
    }

    public Documento save(Documento documento) {
        return documentoRepository.save(documento);
    }

    public void delete(UUID id) {
        documentoRepository.deleteById(id);
    }
}
