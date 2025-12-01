package com.upt.lp.componente2.controller;

import com.upt.lp.componente2.dto.DocumentoDTO;
import com.upt.lp.componente2.entity.Documento;
import com.upt.lp.componente2.mapper.DocumentoMapper;
import com.upt.lp.componente2.service.DocumentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {
    
    private final DocumentoService documentoService;
    
    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }
    
    @GetMapping
    public List<DocumentoDTO> getAllDocumentos() {
        return documentoService.getAllDocumentos()
                .stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public DocumentoDTO getDocumentoById(@PathVariable String id) {
        Documento documento = documentoService.getDocumentoById(id);
        return DocumentoMapper.toDTO(documento);
    }
    
    @PostMapping
    public DocumentoDTO createDocumento(@RequestBody Documento documento,
                                       @RequestParam String estudanteId) {
        Documento novoDocumento = documentoService.createDocumento(documento, estudanteId);
        return DocumentoMapper.toDTO(novoDocumento);
    }
    
    @PutMapping("/{id}")
    public DocumentoDTO updateDocumento(@PathVariable String id, @RequestBody Documento documento) {
        Documento documentoAtualizado = documentoService.updateDocumento(id, documento);
        return DocumentoMapper.toDTO(documentoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deleteDocumento(@PathVariable String id) {
        documentoService.deleteDocumento(id);
    }
    
    @GetMapping("/estudante/{estudanteId}")
    public List<DocumentoDTO> getDocumentosByEstudante(@PathVariable String estudanteId) {
        return documentoService.getDocumentosByEstudante(estudanteId)
                .stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    public List<DocumentoDTO> searchDocumentosByEmpresa(@RequestParam String nomeEmpresa) {
        return documentoService.searchDocumentosByEmpresa(nomeEmpresa)
                .stream()
                .map(DocumentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
