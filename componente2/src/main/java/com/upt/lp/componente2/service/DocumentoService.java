package com.upt.lp.componente2.service;

import com.upt.lp.componente2.entity.Documento;
import com.upt.lp.componente2.entity.Estudante;
import com.upt.lp.componente2.repository.DocumentoRepository;
import com.upt.lp.componente2.repository.EstudanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocumentoService {
    
    private final DocumentoRepository documentoRepository;
    private final EstudanteRepository estudanteRepository;
    
    public DocumentoService(DocumentoRepository documentoRepository,
                           EstudanteRepository estudanteRepository) {
        this.documentoRepository = documentoRepository;
        this.estudanteRepository = estudanteRepository;
    }
    
    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }
    
    public Documento getDocumentoById(String id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado com ID: " + id));
    }
    
    public Documento createDocumento(Documento documento, String estudanteId) {
        // Buscar estudante
        Estudante estudante = estudanteRepository.findById(estudanteId)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + estudanteId));
        
        documento.setEstudante(estudante);
        
        return documentoRepository.save(documento);
    }
    
    public Documento updateDocumento(String id, Documento documentoAtualizado) {
        Documento documentoExistente = getDocumentoById(id);
        
        documentoExistente.setNomeEmpresa(documentoAtualizado.getNomeEmpresa());
        documentoExistente.setContactoEmpresa(documentoAtualizado.getContactoEmpresa());
        documentoExistente.setObjetivoEstagio(documentoAtualizado.getObjetivoEstagio());
        
        // Atualizar estudante se fornecido
        if (documentoAtualizado.getEstudante() != null) {
            documentoExistente.setEstudante(documentoAtualizado.getEstudante());
        }
        
        return documentoRepository.save(documentoExistente);
    }
    
    public void deleteDocumento(String id) {
        if (!documentoRepository.existsById(id)) {
            throw new RuntimeException("Documento não encontrado com ID: " + id);
        }
        documentoRepository.deleteById(id);
    }
    
    public List<Documento> getDocumentosByEstudante(String estudanteId) {
        return documentoRepository.findByEstudanteId(estudanteId);
    }
    
    public List<Documento> searchDocumentosByEmpresa(String nomeEmpresa) {
        return documentoRepository.findByNomeEmpresaContainingIgnoreCase(nomeEmpresa);
    }
}
